package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.component.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TicketDTO {

    private Integer ticketId;
    private String title;
    private Date creationDate;
    private String priority;
    private String category;
    private Status status;
    private UserDTO user;
    private List<AttachmentDTO> attachments;
    private List<CommentDTO> comments;
}
