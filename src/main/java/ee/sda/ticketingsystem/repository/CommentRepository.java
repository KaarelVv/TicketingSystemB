package ee.sda.ticketingsystem.repository;

import ee.sda.ticketingsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Integer> {
}
