package com.temperature_batch.infraestructure.job.configuration;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.infraestructure.job.joblistener.JobExecutionListener;
import com.temperature_batch.infraestructure.job.step1.item.ConsoleItemWriter;
import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemReaderListener;
import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemWriterListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;



@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classpath*:/data/inputData*.csv")
    private Resource[] inputResources;

    private final Resource outPutResource = new FileSystemResource("output/outputData.csv");


    @Bean
    public WeatherItemReaderListener readerListener(){
        return new WeatherItemReaderListener();
    }
    @Bean
    public WeatherItemWriterListener writerListener(){
        return new WeatherItemWriterListener();
    }

    @Bean
    public Job temperatureRegisterJob(Step step1, JobExecutionListener jobExecutionListener){
        return jobBuilderFactory
                .get("temperatureRegisterJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .start(step1)
                .build();

    }

    @Bean
    public Step step1(WeatherItemReaderListener readerListener,
                      WeatherItemWriterListener writerListener){
        return stepBuilderFactory.get("step1")
                .<Weather, Weather>chunk(5)
                .listener(readerListener)
                .listener(writerListener)
                .faultTolerant()
                .noSkip(IllegalArgumentException.class)
                .reader(multiResourceItemReader())
                .writer(writer())
                .build();
    }


    @Bean
    public MultiResourceItemReader<Weather> multiResourceItemReader(){
        MultiResourceItemReader<Weather> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setStrict(true);
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

    @Bean
    public ConsoleItemWriter writer(){
        return new ConsoleItemWriter();
    }
}
