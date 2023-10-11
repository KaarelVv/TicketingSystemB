package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.entity.Ticket;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
public class CommentDTO {

    private Long id;
    private Ticket ticket;
    private String content;
    private Date commentDate;
}
