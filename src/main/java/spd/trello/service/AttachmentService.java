package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spd.trello.domain.Attachment;
import spd.trello.repository.AttachmentDAO;

@Service
public class AttachmentService extends ServiceLayer<Attachment, AttachmentDAO> {
    @Autowired
    public AttachmentService(AttachmentDAO repository) {
        super(repository);
    }
}
