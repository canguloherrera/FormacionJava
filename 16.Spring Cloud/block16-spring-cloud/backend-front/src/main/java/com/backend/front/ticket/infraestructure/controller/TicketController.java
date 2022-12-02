package com.backend.front.ticket.infraestructure.controller;

import com.backend.front.ticket.application.TicketService;
import com.backend.front.ticket.infraestructure.dto.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")

public class TicketController {
   @Autowired
   TicketService ticketService;

    @PostMapping("ticket/{idClient}/{idTrip}")
    public Object addTicket(@PathVariable Long idClient,@PathVariable Integer idTrip){
        return ticketService.createTicket(idClient,idTrip);
    }

    @GetMapping("/listTicket")
    public List<TicketOutputDto> listTicket(){
        return ticketService.getAllTicket();

    }

}
