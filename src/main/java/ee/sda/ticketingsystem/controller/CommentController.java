package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.repository.CommentRepository;
import ee.sda.ticketingsystem.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {


    private CommentService commentService;

    @PostMapping
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }

}
