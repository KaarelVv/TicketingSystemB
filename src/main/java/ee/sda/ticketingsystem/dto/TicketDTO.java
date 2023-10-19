package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.enums.ticket.Priority;
import ee.sda.ticketingsystem.enums.ticket.Status;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class TicketDTO {

    private Integer id;
    private String title;
    @Size(max = 2000, message = "Cannot be longer than 2000 characters")
    private String description;
    private Date creationDate;
    private Priority priority;
    private Status status;
    private Integer userId;
    private List<CommentDTO> comments = new ArrayList<>();

}
