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
                .setId(ticket.getId())
                .setTitle(ticket.getTitle())
                .setDescription(ticket.getDescription())
                .setCreationDate(ticket.getCreationDate())
                .setPriority(ticket.getPriority())
                .setStatus(ticket.getStatus())
                .setUserId(ticket.getUser().getId());
    }

    public Ticket convertToEntity(TicketDTO dto) {
        return new Ticket()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setCreationDate(dto.getCreationDate())
                .setPriority(dto.getPriority())
                .setStatus(dto.getStatus());
    }
}
