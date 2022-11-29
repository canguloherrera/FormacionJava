package com.backend.client.infraestructure.dto;

import com.backend.trip.domain.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientInputDto {

    private String name;
    private String surname;
    private String age;
    private String email;
    private String phoneNumber;
    private Trip trip;
}
