package ee.sda.ticketingsystem.service;

import ee.sda.ticketingsystem.hydrator.AttachmentHydrator;
import ee.sda.ticketingsystem.repository.AttachmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService {

    private AttachmentRepository attachmentRepository;
    private AttachmentHydrator attachmentHydrator;


}
