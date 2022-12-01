package com.backend.front.client.infraestructure.dto;

import com.backend.front.client.domain.Client;
import lombok.Data;

@Data
public class ClientOutputDto {
    Long idClient;
    String name;
    String surname;
    Integer age;
    String email;
    String phoneNumber;

//public ClientOutputDto(Client client){
//        this.id = client.getId();
//        this.name = client.getName();
//        this.surname = client.getSurname();
//        this.age = client.getAge();
//        this.email= client.getEmail();
//        this.phoneNumber = client.getPhoneNumber();
//    }
}
