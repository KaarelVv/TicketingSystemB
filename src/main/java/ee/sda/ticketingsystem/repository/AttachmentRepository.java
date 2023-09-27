package ee.sda.ticketingsystem.repository;

import ee.sda.ticketingsystem.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository <Attachment, Integer> {
}
