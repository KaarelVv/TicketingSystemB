package ee.sda.ticketingsystem.repository;

import ee.sda.ticketingsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
