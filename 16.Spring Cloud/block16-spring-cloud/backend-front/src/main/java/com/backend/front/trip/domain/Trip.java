package com.backend.front.trip.domain;

import com.backend.front.client.domain.Client;
import com.backend.front.trip.infraestructure.dto.TicketInputDto;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
   private  Long id;
   private String origin;
   private String destination;
   private Date departureDate;
   private Date arrivalDate;
   private  String status;

    public Trip(TripOutputDto tripOutputDto){
        this.id = tripOutputDto.getId();
        this.origin= tripOutputDto.getOrigin();
        this.destination = tripOutputDto.getDestination();
        this.departureDate = tripOutputDto.getDepartureDate();
        this.arrivalDate = tripOutputDto.getArrivalDate();
        this.status = tripOutputDto.getStatus();

    }
}
