package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;


}
