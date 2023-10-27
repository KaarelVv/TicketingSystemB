package ee.sda.ticketingsystem.controller.ticket;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.service.CommentService;
import ee.sda.ticketingsystem.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket/customer")
@AllArgsConstructor
//@PreAuthorize("hasRole('CUSTOMER')")
public class UserTicketController {

    private final TicketService ticketService;
    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Integer id) {
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        return ResponseEntity.ok().body(ticketDTO);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<TicketDTO>> getAllByUser(@PathVariable Integer id) {
        List<TicketDTO> ticketDTO = ticketService.getAllTicketsByUserId(id);
        return ResponseEntity.ok().body(ticketDTO);
    }





}
