package ee.sda.ticketingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date changeDate;
    private String oldStatus;
    private String newStatus;
    private String oldPriority;
    private String newPriority;
    private Integer changeByAgentId;
    @ManyToOne
    private Ticket ticket;

}
