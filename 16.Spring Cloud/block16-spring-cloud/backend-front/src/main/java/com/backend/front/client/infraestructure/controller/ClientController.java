package com.backend.front.client.infraestructure.controller;

import com.backend.front.client.application.ClientService;
import com.backend.front.client.domain.Client;
import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/client/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable("id") Long id){
        Object  client = clientService.showById(id);
        if(client == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
