package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    TicketRepository ticketRepository;

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Integer id){
        return ticketRepository.findById(id).get();
    }
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    @PostMapping
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    @PutMapping
    public Ticket editTicket() {
        return null;
    }


}
