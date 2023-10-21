package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.enums.ticket.Priority;
import ee.sda.ticketingsystem.enums.ticket.Status;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.exception.UserNotFoundException;
import ee.sda.ticketingsystem.hydrator.TicketHydrator;
import ee.sda.ticketingsystem.repository.TicketRepository;
import ee.sda.ticketingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;
    private TicketHydrator ticketHydrator;
    private UserRepository userRepository;

    @Value("${ticket.defaultStatus}")
    private String DEFAULT_STATUS;

    @Value("${ticket.defaultPriority}")
    private String DEFAULT_PRIORITY;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketHydrator ticketHydrator, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketHydrator = ticketHydrator;
        this.userRepository = userRepository;
    }

    @Transactional
    public TicketDTO createTicket(TicketDTO ticketDTO) {

        User user = userRepository.findById(ticketDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id:" + ticketDTO.getUserId() + " not found"));

        Ticket ticket = ticketHydrator.convertToEntity(ticketDTO)
                .setStatus(Status.valueOf(DEFAULT_STATUS))
                .setPriority(Priority.valueOf(DEFAULT_PRIORITY))
                .setCreationDate(new Date())
                .setUser(user);
        System.out.println("Ticket created!");   // Maybe change later to logger

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketHydrator.convertToDTO(savedTicket);
    }

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticket -> ticketHydrator.convertToDTO(ticket))
                .collect(Collectors.toList());
    }
    public List<TicketDTO> getAllTicketsByUserId(Integer integer) {
        List<Ticket> tickets = ticketRepository.findByUserId(integer);
        return tickets.stream().map(ticket -> ticketHydrator.convertToDTO(ticket))
                .collect(Collectors.toList());
    }

    public TicketDTO getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id:" + id + " not found"));

        return ticketHydrator.convertToDTO(ticket);
    }

    @Transactional
    public TicketDTO editTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(ticketDTO.getId())
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id:" + ticketDTO.getId() + " not found"))
                .setTitle(ticketDTO.getTitle())
                .setDescription(ticketDTO.getDescription())
                .setStatus(ticketDTO.getStatus())
                .setPriority(ticketDTO.getPriority());

        return ticketHydrator.convertToDTO(ticket);
    }


}
