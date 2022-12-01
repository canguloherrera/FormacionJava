package com.backend.front.client.domain;


import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
   private  Long idClient;
   private String name;
   private String surname;
    private Integer age;
   private  String email;
   private String phoneNumber;


public Client (ClientOutputDto clientOutputDto){
        this.idClient = clientOutputDto.getIdClient();
        this.name = clientOutputDto.getName();
        this.surname = clientOutputDto.getSurname();
        this.age = clientOutputDto.getAge();
        this.email = clientOutputDto.getEmail();
        this.phoneNumber = clientOutputDto.getPhoneNumber();


 }


}
