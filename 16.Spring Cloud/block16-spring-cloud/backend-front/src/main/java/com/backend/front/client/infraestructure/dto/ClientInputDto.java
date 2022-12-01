package com.backend.front.client.infraestructure.dto;

import lombok.Data;

@Data
public class ClientInputDto {
    String name;
    String surname;
    Integer age;
    String email;
    String phone_number;
}
