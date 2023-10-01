package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.enums.Status;
import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.hydrator.TicketiHydrator;
import ee.sda.ticketingsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    TicketRepository ticketRepository;
    TicketiHydrator ticketiHydrator;

    @Transactional
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketiHydrator.convertToEntity(ticketDTO);
        ticket.setStatus(Status.OPEN);
        ticket.setCreationDate(new Date());
        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketiHydrator.convertToDTO(savedTicket);
    }

    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    // Kuigi pakkus algselt teha Optional<Ticket> .orElseThrow() asemel
    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id:" + id));
    }
    @Transactional
    // Doesn´t work without Optional
    public TicketDTO editTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketiHydrator.convertToEntity(ticketDTO);
        if (!ticketRepository.existsById(ticket.getTicketId())) {
            throw new TicketNotFoundException("Ticket not found with id " + ticket.getTicketId());
        }
        ticketRepository.save(ticket);
        return ticketiHydrator.convertToDTO(ticket);
    }


}
