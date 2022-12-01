package com.backend.front.trip.infraestructure.controller;

import com.backend.front.client.application.ClientService;
import com.backend.front.trip.application.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    TripService tripService;

    @GetMapping("/trip/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable("id") Integer id){
        Object  trip = tripService.showById(id);
        if(trip == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trip);
    }


}
