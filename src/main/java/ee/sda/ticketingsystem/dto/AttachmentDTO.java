package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.entity.Ticket;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors
public class AttachmentDTO {

    private Integer attachmentId;
    private String fileName;
    private Integer fileSize;
    private Date uploadDate;
    private Ticket ticket;
}
