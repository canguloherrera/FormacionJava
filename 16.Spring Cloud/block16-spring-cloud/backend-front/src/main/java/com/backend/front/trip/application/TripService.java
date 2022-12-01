package com.backend.front.trip.application;

import com.backend.front.trip.feign.TripFeign;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    @Autowired
    TripFeign tripFeignService;
    public TripOutputDto showById(Integer id) {
        //return new ClientOutputDto(clientFeign.showById(id));
        return tripFeignService.getTripById(id);

    }

    public TripOutputDto addPassengerToTrip(Integer idTrip,Long idClient){
        return tripFeignService.addPassengerToTrip(idTrip,idClient);
    }
}
