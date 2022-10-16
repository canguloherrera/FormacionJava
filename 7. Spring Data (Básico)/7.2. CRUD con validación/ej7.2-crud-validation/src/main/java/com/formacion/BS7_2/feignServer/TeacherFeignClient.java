package com.formacion.BS7_2.feignServer;
import com.formacion.BS7_2.configuration.FeignClientConfig;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;

@FeignClient(name = "feignClient", url = "http://localhost:8080", configuration = FeignClientConfig.class)
public interface TeacherFeignClient {

    @GetMapping("/teacher/{id}")
    public TeacherOutputDto getTeacherWithFeign(@PathVariable("id") String idProfessor);
}
