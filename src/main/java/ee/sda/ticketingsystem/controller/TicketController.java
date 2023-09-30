package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.repository.TicketRepository;
import ee.sda.ticketingsystem.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    TicketService ticketService;

    // localhost:8080/ticket/12
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Integer id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok().body(ticket);
    }
    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getAllTicket();
    }
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> editTicket(@PathVariable Integer id, Ticket updatedTicket) {
        try {
            Ticket editedTicket = ticketService.editTicket(id, updatedTicket);
            return ResponseEntity.ok(editedTicket);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }


}
