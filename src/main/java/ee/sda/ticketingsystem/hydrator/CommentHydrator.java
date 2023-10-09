package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentHydrator {

    public CommentDTO commentToDTO(Comment comment){
        return new CommentDTO()
                .setCommentId(comment.getCommentId())
                .setCommentDate(comment.getCommentDate())
                .setContent(comment.getContent());
    }

    public Comment commentToEntity(CommentDTO dto){
        return new Comment()
                .setCommentId(dto.getCommentId())
                .setCommentDate(dto.getCommentDate())
                .setContent(dto.getContent());
    }
}
