package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentHydrator {

    public CommentDTO convertToDTO(Comment comment){
        return new CommentDTO()
                .setId(comment.getId())
                .setCommentDate(comment.getCommentDate())
                .setContent(comment.getContent())
                .setTicketId(comment.getTicket().getId());
    }

    public Comment convertToEntity(CommentDTO dto){
        return new Comment()
                .setId(dto.getId())
                .setCommentDate(dto.getCommentDate())
                .setContent(dto.getContent());
    }
}
