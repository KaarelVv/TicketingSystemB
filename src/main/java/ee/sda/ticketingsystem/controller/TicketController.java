package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private TicketService ticketService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Ticket> getTicket(@PathVariable Integer id) {
//        Ticket ticket = ticketService.getTicketById(id);
//        return ResponseEntity.ok().body(ticket);
//    }






}
