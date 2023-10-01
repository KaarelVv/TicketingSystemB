package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketHydrator {
    public TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setTicketId(ticket.getTicketId());
        dto.setTitle(ticket.getTitle());
        dto.setCreationDate(ticket.getCreationDate());
        dto.setPriority(ticket.getPriority());
        dto.setCategory(ticket.getCategory());
        dto.setStatus(ticket.getStatus());
        return dto;
    }

    public Ticket convertToEntity(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(dto.getTicketId());
        ticket.setTitle(dto.getTitle());
        ticket.setCreationDate(dto.getCreationDate());
        ticket.setPriority(dto.getPriority());
        ticket.setCategory(dto.getCategory());
        ticket.setStatus(dto.getStatus());

        return ticket;
    }}
