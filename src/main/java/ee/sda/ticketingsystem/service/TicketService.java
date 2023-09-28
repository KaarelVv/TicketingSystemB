package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }


    // Kuigi pakkus algselt teha Optional<Ticket> .orElseThrow() asemel
    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow();
    }

    // Doesn´t work without Optional
    public Ticket editTicket(Integer id, Ticket updateTicket) {
        Optional<Ticket> existingTicketOptional = ticketRepository.findById(id);
        if(existingTicketOptional.isPresent()) {
            Ticket existingTicket = existingTicketOptional.get();
    }

        return ticketRepository.save(updateTicket);
    }

}
