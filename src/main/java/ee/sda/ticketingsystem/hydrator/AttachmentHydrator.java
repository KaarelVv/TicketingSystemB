package ee.sda.ticketingsystem.hydrator;

import ee.sda.ticketingsystem.dto.AttachmentDTO;
import ee.sda.ticketingsystem.entity.Attachment;
import org.springframework.stereotype.Component;

@Component
public class AttachmentHydrator {



    public AttachmentDTO convertToDTO(Attachment attachment){
        return new AttachmentDTO()
                .setId(attachment.getId())
                .setFileName(attachment.getFileName())
                .setFileSize(attachment.getFileSize())
                .setUploadDate(attachment.getUploadDate())
                .setTicketId(attachment.getTicket().getId());
    }
    public Attachment convertToEntity(AttachmentDTO dto){
        return new Attachment()
                .setId(dto.getId())
                .setFileName(dto.getFileName())
                .setFileSize(dto.getFileSize())
                .setUploadDate(dto.getUploadDate());
    }


}
