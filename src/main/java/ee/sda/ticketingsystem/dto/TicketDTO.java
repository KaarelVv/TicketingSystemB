package ee.sda.ticketingsystem.dto;

import ee.sda.ticketingsystem.enums.ticket.Priority;
import ee.sda.ticketingsystem.enums.ticket.Status;
import ee.sda.ticketingsystem.validation.NoBannedWords;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NoBannedWords
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title should be between 1 to 100 characters")
    private String title;
    @NoBannedWords
    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Cannot be longer than 2000 characters")
    private String description;
    private Date creationDate;
    private Priority priority;
    private Status status;
    @NotNull(message = "User ID is required")
    private Integer userId;
    private List<CommentDTO> comments = new ArrayList<>();
    private List<HistoryLogDTO> historyLog = new ArrayList<>();

}
