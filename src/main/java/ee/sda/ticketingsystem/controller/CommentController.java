package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comments")
@AllArgsConstructor
public class CommentController {


    private CommentService commentService;

    @PostMapping
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }

}
