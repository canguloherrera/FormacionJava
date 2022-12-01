package com.backend.front.trip.infraestructure.dto;

import com.backend.front.ticket.domain.Ticket;
import com.backend.front.client.infraestructure.dto.ClientOutputDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripOutputDto {
    Long id;
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;

    String status;

    List<ClientOutputDto> passengers;

    public TripOutputDto(Ticket ticket){
        this.id = ticket.getId();
        this.origin = ticket.getTripOrigin();
        this.destination = ticket.getTripDestination();
        this.departureDate = ticket.getDepartureDate();
        this.arrivalDate = ticket.getArrivalDate();


    }
}
