package com.backend.trip.infraestructure.dto;

import com.backend.client.domain.Client;
import com.backend.client.infraestructure.dto.ClientOutputDto;
import com.backend.trip.domain.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripOutputDto {
    private Long id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;

    private Boolean status;

    private List<ClientOutputDto> listPassengers = new ArrayList<>();


    public TripOutputDto(Trip trip){
        this.id = trip.getIdTrip();
        this.origin = trip.getOrigin();
        this.arrivalDate = trip.getArrivalDate();
        this.departureDate = trip.getDepartureDate();
        this.listPassengers = trip.getPassengers().stream().map(ClientOutputDto::new).toList();
        this.status = trip.getStatus();
        this.destination = trip.getDestination();



    }


}
