package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
@AllArgsConstructor
public class CommentController {


    private CommentService commentService;

    @PostMapping("/{ticketId}")
    public CommentDTO addComment(@PathVariable Integer ticketId, @RequestBody CommentDTO commentDTO){
        commentDTO.setTicketId(ticketId);
        return commentService.createComment(commentDTO);
    }

}
