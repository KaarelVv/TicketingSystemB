package ee.sda.ticketingsystem.controller;

import ee.sda.ticketingsystem.repository.AttachmentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/attachment")
public class AttachmentController {

    private AttachmentRepository attachmentRepository;


}
