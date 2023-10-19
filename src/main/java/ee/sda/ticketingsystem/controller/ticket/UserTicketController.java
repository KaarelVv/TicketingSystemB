package ee.sda.ticketingsystem.controller.ticket;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.service.CommentService;
import ee.sda.ticketingsystem.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ticket/user")
@AllArgsConstructor
//@PreAuthorize("hasRole('USER')")
public class UserTicketController {

    private final TicketService ticketService;
    private final CommentService commentService;
    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Integer id) {
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        return ResponseEntity.ok().body(ticketDTO);
    }



}
