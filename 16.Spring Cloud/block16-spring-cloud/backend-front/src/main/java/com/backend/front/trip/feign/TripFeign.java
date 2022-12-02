package com.backend.front.trip.feign;

import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="trip-service",url = "http://localhost:8080")
public interface TripFeign {
    @GetMapping("trip/{id}")
    TripOutputDto getTripById(@PathVariable Integer id);

    @PutMapping("/addPassenger/{idTrip}/{idClient}")
    TripOutputDto addPassengerToTrip(@PathVariable Integer idTrip,@PathVariable Long idClient);



}
