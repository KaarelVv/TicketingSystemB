package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.entity.Comment;
import ee.sda.ticketingsystem.hydrator.CommentHydrator;
import ee.sda.ticketingsystem.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private CommentHydrator commentHydrator;


    public CommentDTO createComment(CommentDTO commentDTO){

        Comment comment = commentHydrator.commentToEntity(commentDTO)


                .setId(commentDTO.getId())
                .setCommentDate(new Date())
                .setContent(commentDTO.getContent());

        return commentHydrator.commentToDTO(comment) ;
    }
}
