package ee.sda.ticketingsystem.repository;

import ee.sda.ticketingsystem.entity.HistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryLogRepository extends JpaRepository<HistoryLog, Integer> {
}
