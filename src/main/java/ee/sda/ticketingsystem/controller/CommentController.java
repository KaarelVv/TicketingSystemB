package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.repository.CommentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    private CommentRepository commentRepository;
}
