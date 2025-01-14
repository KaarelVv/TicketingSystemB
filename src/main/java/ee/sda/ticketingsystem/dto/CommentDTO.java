package ee.sda.ticketingsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
public class CommentDTO {

    private Integer id;
    private String content;
    private Date commentDate;
    private Integer ticketId;
}
