package com.backend.client.domain;

import com.backend.client.infraestructure.dto.ClientInputDto;
import com.backend.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;


    @NotEmpty(message = "Field can not empty")
    private String name;
    @NotEmpty(message = "Field can not empty")
    private String surname;
    @NotEmpty(message = "Field can not empty")
    private String age;
   @Email
   @Column(unique = true)
    private String email;
   @Column(nullable = false,unique = false)
    private String phoneNumber;

   @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "passengers")
    List<Trip> tripList = new ArrayList<>();


    public Client (ClientInputDto clientInputDto){
        this.name = clientInputDto.getName();
        this.surname = clientInputDto.getSurname();
        this.age = clientInputDto.getAge();
        this.email = clientInputDto.getEmail();
        this.phoneNumber = clientInputDto.getPhoneNumber();
    }

}
