package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.enums.Priority;
import ee.sda.ticketingsystem.enums.Status;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class TicketDTO {

    private Integer ticketId;
    private String title;
    @Size(max = 2000, message = "Cannot be longer than 2000 characters")
    private String description;
    private Date creationDate;
    private Priority priority;
    private Status status;
    private Integer userId;
    private List<AttachmentDTO> attachments = new ArrayList<>();
    private List<CommentDTO> comments = new ArrayList<>();
}
