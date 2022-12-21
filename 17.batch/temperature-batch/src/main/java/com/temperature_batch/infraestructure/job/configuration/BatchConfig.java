package com.temperature_batch.infraestructure.job.configuration;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.dto.WeatherDto;
import com.temperature_batch.infraestructure.dto.WeatherRiskDto;
import com.temperature_batch.infraestructure.job.joblistener.JobExecutionListener;
import com.temperature_batch.infraestructure.job.step1.item.WheatherItemWriter;
import com.temperature_batch.infraestructure.job.step1.item.WeatherItemProcessor;
import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemProcessorListener;
import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemReaderListener;

import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemWriterListener;

import com.temperature_batch.infraestructure.job.step2.item.WeatherProcessor;
import com.temperature_batch.infraestructure.job.step2.listener.WeatherItemReaderErrorListener;
import com.temperature_batch.infraestructure.job.step3.item.WeatherRiskItemProcesor;
import com.temperature_batch.infraestructure.job.step3.item.WeatherRiskItemWriter;
import com.temperature_batch.infraestructure.job.step3.mapper.WeatherRiskDtoRowMapper;
import com.temperature_batch.infraestructure.job.step3.mapper.WeatherRiskRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classpath*:/data/inputData*.csv")
    private Resource[] inputResources;

    @Autowired
    DataSource dataSource;

    private final Resource outPutResource = new FileSystemResource("output/outputData1.csv");


    @Bean
    public WeatherItemReaderListener readerListener(){
        return new WeatherItemReaderListener();
    }
    @Bean
    public WeatherItemWriterListener writerListener(){
        return new WeatherItemWriterListener();
    }
    @Bean
    public WeatherItemProcessorListener weatherItemProcessorListener(){
        return new WeatherItemProcessorListener();
    }
    @Bean
    public WeatherItemProcessor weatherItemProcessor(){
        return new WeatherItemProcessor();
    }

    @Bean
    public WheatherItemWriter writer(){
        return new WheatherItemWriter();
    }


    @Bean
    public WeatherRiskItemProcesor weatherRiskItemProcesor(){
        return  new WeatherRiskItemProcesor();
    }

    @Bean
    public WeatherRiskItemWriter weatherRiskItemWriter(){
        return new WeatherRiskItemWriter();
    }
    @Bean
    public WeatherItemReaderErrorListener weatherItemReaderErrorListener(){
        return new WeatherItemReaderErrorListener();
    }

    @Bean
    public Job temperatureRegisterJob(Step step1, Step step2,JobExecutionListener jobExecutionListener){
        return jobBuilderFactory
                .get("temperatureRegisterJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .start(step1)
                .next(step2)
                .next(step3())
                .build();

    }
    //only allowed to save values in range
    @Bean
    public Step step1(WeatherItemReaderListener readerListener,
                      WeatherItemWriterListener writerListener,
                      WeatherItemProcessorListener processorListener,
                      WeatherItemProcessor weatherItemProcessor){
        return stepBuilderFactory.get("step1")
                .<Weather, Weather>chunk(5)
                .listener(readerListener)
                .listener(writerListener)
                .listener(processorListener)
                .faultTolerant()
                .skipLimit(10)
                .skip(IllegalArgumentException.class)
                .reader(multiResourceItemReader())
                .processor(weatherItemProcessor)
                .writer(writer())
                .build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .<Weather,WeatherDto>chunk(10)
                .reader(multiResourceItemReader())
                .processor(new WeatherProcessor())
                .writer(writerFlatPlaneDtoWithError())
                .build();}


    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .<WeatherRiskDto,WeatherRisk>chunk(10)
                .reader(readerJDBC())
                .processor(weatherRiskItemProcesor())
                .writer(weatherRiskItemWriter())
                .build();

    }



    @Bean
    public MultiResourceItemReader<Weather> multiResourceItemReader(){
        MultiResourceItemReader<Weather> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(reader());
        return resourceItemReader;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FlatFileItemReader<Weather> reader(){
        FlatFileItemReader<Weather> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<>(){
            {
                //3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(new String[]{"location","date","temperature"});
                    }
                });
                //set values  in Employee Class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Weather>(){
                    {
                        setTargetType(Weather.class);
                    }
                });
            }
        });
        return reader;
    }




    public FlatFileItemWriter<WeatherDto> writerFlatPlaneDtoWithError(){

        //CREATE WRITER INSTANCE
        FlatFileItemWriter<WeatherDto> writer = new FlatFileItemWriter<>();
        //setup output file location

        writer.setResource(outPutResource);
        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);
        //Name field values sequence based on object properties
        writer.setLineAggregator(new DelimitedLineAggregator<WeatherDto>(){
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<WeatherDto>(){
                    {
                        setNames(new String [] {"location","date","temperature"});
                    }
                });
            }
        });
        return writer;

    }

    @Bean
    public JdbcCursorItemReader<WeatherRiskDto> readerJDBC(){
        JdbcCursorItemReader<WeatherRiskDto> reader = new JdbcCursorItemReader<>();


   reader.setSql("SELECT t.location,YEAR(t.date) as y,MONTH(t.date) " +
               "as m,COUNT(t.temperature) as c,AVG(t.temperature)" +
                " as average FROM weather AS t " +
                "GROUP BY t.location,YEAR(t.date),MONTH(t.date) ORDER BY t.location");
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new WeatherRiskDtoRowMapper());
        return reader;
    }





}
