package com.backend.front.client.feign;




import com.backend.front.client.domain.Client;
import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

@FeignClient(name="client-service",url = "http://localhost:8100/client")
public interface ClientFeign {
    @GetMapping("/{id}")
    ClientOutputDto showById(@PathVariable("id") Long id);


//    @PostMapping("/addclient")
//    public ClientOutputDto addClient(@RequestBody ClientInputDto clientInputDto);
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteClient(@PathVariable Long id);



}
