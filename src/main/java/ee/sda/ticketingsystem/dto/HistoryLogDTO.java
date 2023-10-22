package ee.sda.ticketingsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class HistoryLogDTO {
    private Integer id;

    private Date changeDate;
    private String oldStatus;
    private String newStatus;
    private String oldPriority;
    private String newPriority;
    private Integer changeByAgentId;

}
