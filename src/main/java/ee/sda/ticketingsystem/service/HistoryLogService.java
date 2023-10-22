package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.entity.HistoryLog;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.repository.HistoryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryLogService {


    private final HistoryLogRepository historyLogRepository;

    @Autowired
    public HistoryLogService(HistoryLogRepository historyLogRepository) {
        this.historyLogRepository = historyLogRepository;
    }

    public void createLog(Ticket oldTicket, Ticket newTicket, Integer agent){
        HistoryLog log = new HistoryLog();
        log.setChangeDate(new Date());
        log.setOldStatus(oldTicket.getStatus().toString());
        log.setNewStatus(newTicket.getStatus().toString());
        log.setOldPriority(oldTicket.getPriority().toString());
        log.setNewPriority(newTicket.getPriority().toString());
        log.setChangeByAgentId(agent);
        historyLogRepository.save(log);
    }
}
