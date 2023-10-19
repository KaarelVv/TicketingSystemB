package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.dto.CommentDTO;
import ee.sda.ticketingsystem.entity.Comment;
import ee.sda.ticketingsystem.entity.Ticket;
import ee.sda.ticketingsystem.exception.TicketNotFoundException;
import ee.sda.ticketingsystem.hydrator.CommentHydrator;
import ee.sda.ticketingsystem.repository.CommentRepository;
import ee.sda.ticketingsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private CommentHydrator commentHydrator;
    private TicketRepository ticketRepository;

    @Transactional
    public CommentDTO createComment(CommentDTO commentDTO){

        Ticket ticket = ticketRepository.findById(commentDTO.getTicketId())
                .orElseThrow(() -> new TicketNotFoundException(""));

        Comment comment = commentHydrator.convertToEntity(commentDTO)
                .setContent(commentDTO.getContent())
                .setCommentDate(new Date())
                .setTicket(ticket);

        Comment savedComment = commentRepository.save(comment);

        return commentHydrator.convertToDTO(savedComment);
    }
    public CommentDTO editComment(CommentDTO commentDTO){

        Comment comment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new RuntimeException("Comment not found"))
                .setContent(commentDTO.getContent());

        return commentHydrator.convertToDTO(comment);
    }
}
