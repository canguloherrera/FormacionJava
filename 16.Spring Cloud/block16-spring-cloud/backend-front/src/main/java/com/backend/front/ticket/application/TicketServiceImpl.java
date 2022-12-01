package com.backend.front.ticket.application;

import com.backend.front.client.application.ClientService;
import com.backend.front.client.domain.Client;
import com.backend.front.client.feign.ClientFeign;

import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import com.backend.front.ticket.application.TicketService;
import com.backend.front.ticket.domain.Ticket;
import com.backend.front.ticket.infraestructure.dto.TicketOutputDto;
import com.backend.front.ticket.infraestructure.repository.TicketRepository;
import com.backend.front.trip.application.TripService;
import com.backend.front.trip.domain.Trip;
import com.backend.front.trip.feign.TripFeign;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepo;

//    @Autowired
//    ClientFeign clientServiceFeign;

     @Autowired
    ClientService clientService;

   @Autowired
   TripService tripService;


    @Override
    public Object createTicket(Long idPassenger,Integer idTrip){
        ClientOutputDto clientOutputDto = clientService.showById(idPassenger);
        Client client = new Client(clientOutputDto);
        TripOutputDto tripOutputDto = tripService.showById(idTrip);
        Trip trip = new Trip(tripOutputDto);
       // TripOutputDto tripOutputDto = tripService.showById(idTrip);
        Ticket ticket = new Ticket(client,trip);
        ticketRepo.save(ticket);
        return ticket;

    }

    @Override
    public List<TicketOutputDto> getAllTicket() {

        return ticketRepo.findAll().stream().map(TicketOutputDto::new).toList();


    }

    @Override
    public TicketOutputDto findTicketById(Long id) {
        return new TicketOutputDto(ticketRepo.findById(id).orElseThrow(()->new RuntimeException("does not exist")));
    }

    @Override
    public void deleteTicket(Long id) {

    }

    @Override
    public ClientOutputDto getClient(Long id) {

        //return clientServiceFeign.getClientById(id);
        return null;
        }
    }

