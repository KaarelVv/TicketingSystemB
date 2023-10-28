package ee.sda.ticketingsystem.services;

import ee.sda.ticketingsystem.dto.TicketDTO;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.hydrator.TicketHydrator;
import ee.sda.ticketingsystem.repository.HistoryLogRepository;
import ee.sda.ticketingsystem.repository.TicketRepository;
import ee.sda.ticketingsystem.repository.UserRepository;
import ee.sda.ticketingsystem.service.HistoryLogService;
import ee.sda.ticketingsystem.service.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TicketHydrator ticketHydrator;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HistoryLogRepository historyLogRepository;

    @Mock
    private HistoryLogService historyLogService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(ticketService, "DEFAULT_STATUS", "OPEN");
        ReflectionTestUtils.setField(ticketService, "DEFAULT_PRIORITY", "MEDIUM");
    }

    @BeforeAll
    public static void  log(){
        System.out.println("Testing ticket service");
    }

    @Test
    void testCreateTicket() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUserId(1);

        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Ticket ticket = new Ticket();
        when(ticketHydrator.convertToEntity(ticketDTO)).thenReturn(ticket);
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(ticketHydrator.convertToDTO(ticket)).thenReturn(ticketDTO);

        TicketDTO result = ticketService.createTicket(ticketDTO);
        assertEquals(ticketDTO, result);
    }

    @Test
    void testGetAllTickets() {
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        List<Ticket> ticketList = Arrays.asList(ticket1, ticket2);

        when(ticketRepository.findAll()).thenReturn(ticketList);
        when(ticketHydrator.convertToDTO(ticket1)).thenReturn(new TicketDTO());
        when(ticketHydrator.convertToDTO(ticket2)).thenReturn(new TicketDTO());

        List<TicketDTO> results = ticketService.getAllTickets();
        assertEquals(2, results.size());
    }

    @Test
    void testGetAllTicketsByUserId() {
        Ticket ticket = new Ticket();
        List<Ticket> ticketList = Arrays.asList(ticket);

        when(ticketRepository.findByUserId(1)).thenReturn(ticketList);
        when(ticketHydrator.convertToDTO(ticket)).thenReturn(new TicketDTO());

        List<TicketDTO> results = ticketService.getAllTicketsByUserId(1);
        assertEquals(1, results.size());
    }

    @Test
    void testGetTicketById() {
        Ticket ticket = new Ticket();
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        when(ticketHydrator.convertToDTO(ticket)).thenReturn(new TicketDTO());

        TicketDTO result = ticketService.getTicketById(1);
        assertNotNull(result);
    }

    @Test
    void testEditTicket() {
        // Setup test data
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(1);
        ticketDTO.setTitle("New Title");
        ticketDTO.setDescription("New Description");

        Ticket oldTicket = mock(Ticket.class);
        Ticket copiedTicket = mock(Ticket.class);
        when(oldTicket.copy()).thenReturn(copiedTicket);

        TicketDTO updatedTicketDTO = new TicketDTO();
        updatedTicketDTO.setId(1);
        updatedTicketDTO.setTitle("New Title");
        updatedTicketDTO.setDescription("New Description");

        // Mock interactions
        when(ticketRepository.findById(1)).thenReturn(Optional.of(oldTicket));
        when(ticketHydrator.convertToDTO(copiedTicket)).thenReturn(updatedTicketDTO);
        doNothing().when(historyLogService).createLog(any(Ticket.class), any(Ticket.class), anyInt());

        // Call the method
        TicketDTO result = ticketService.editTicket(ticketDTO);

        // Assert the results
        assertEquals(ticketDTO.getTitle(), result.getTitle());
        assertEquals(ticketDTO.getDescription(), result.getDescription());

        // Verify mock interactions
        verify(historyLogService).createLog(any(Ticket.class), any(Ticket.class), anyInt());
    }
}
