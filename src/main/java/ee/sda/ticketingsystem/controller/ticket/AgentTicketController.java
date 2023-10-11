package ee.sda.ticketingsystem.controller.ticket;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket/agent")
@AllArgsConstructor
//@PreAuthorize("hasRole('AGENT')")
public class AgentTicketController {

    private final TicketService ticketService;

    @GetMapping
    public List<TicketDTO> getTickets() {
        return ticketService.getAllTicket();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> editTicket(@PathVariable Integer id, @RequestBody TicketDTO updatedTicket) {
        try {
            updatedTicket.setId(id);
            TicketDTO editedTicket = ticketService.editTicket(updatedTicket);
            return ResponseEntity.ok(editedTicket);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Integer id) {
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        return ResponseEntity.ok().body(ticketDTO);
    }
}
