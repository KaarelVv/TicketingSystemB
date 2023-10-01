package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.component.Status;
import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class TicketService {


    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    TicketRepository ticketRepository;

    @Transactional
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity(ticketDTO);
        ticket.setStatus(Status.OPEN);
        ticket.setCreationDate(new Date());

        Ticket savedTicket = ticketRepository.save(ticket);

        return convertToDTO(savedTicket);
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

        Ticket ticket = convertToEntity(ticketDTO);

        if (!ticketRepository.existsById(ticket.getTicketId())) {
            throw new TicketNotFoundException("Ticket not found with id " + ticket.getTicketId());
        }

        ticketRepository.save(ticket);
        return convertToDTO(ticket);
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setTicketId(ticket.getTicketId());
        dto.setTitle(ticket.getTitle());
        dto.setCreationDate(ticket.getCreationDate());
        dto.setPriority(ticket.getPriority());
        dto.setCategory(ticket.getCategory());
        dto.setStatus(ticket.getStatus());
        return dto;
    }

    private Ticket convertToEntity(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(dto.getTicketId());
        ticket.setTitle(dto.getTitle());
        ticket.setCreationDate(dto.getCreationDate());
        ticket.setPriority(dto.getPriority());
        ticket.setCategory(dto.getCategory());
        ticket.setStatus(dto.getStatus());

        return ticket;
    }
}
