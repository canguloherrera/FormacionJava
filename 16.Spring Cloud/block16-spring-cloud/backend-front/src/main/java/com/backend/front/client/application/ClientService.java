package com.backend.front.client.application;

import com.backend.front.client.domain.Client;
import com.backend.front.client.feign.ClientFeign;
import com.backend.front.client.infraestructure.dto.ClientInputDto;
import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService  {
    @Autowired
    ClientFeign clientFeign;


    public ClientOutputDto showById(Long id) {
        //return new ClientOutputDto(clientFeign.showById(id));
        return clientFeign.showById(id);

    }
}
