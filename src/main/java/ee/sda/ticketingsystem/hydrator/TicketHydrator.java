package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Accessors(chain = true)
public class TicketHydrator {
    public TicketDTO convertToDTO(Ticket ticket) {
        return new TicketDTO()
                .setTicketId(ticket.getTicketId())
                .setTitle(ticket.getTitle())
                .setDescription(ticket.getDescription())
                .setCreationDate(ticket.getCreationDate())
                .setPriority(ticket.getPriority())
                .setStatus(ticket.getStatus())
                .setUserId(ticket.getUser().getUserId());
    }

    public Ticket convertToEntity(TicketDTO dto) {
        return new Ticket()
                .setTicketId(dto.getTicketId())
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setCreationDate(dto.getCreationDate())
                .setPriority(dto.getPriority())
                .setStatus(dto.getStatus());
    }
}
