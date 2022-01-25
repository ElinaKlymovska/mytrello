package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Attachment;
import spd.trello.repository.IRepository;

@Service
public class AttachmentService extends ServiceLayer<Attachment> {
    public AttachmentService(IRepository<Attachment> repository) {
        super(repository);
    }
}
