package spd.trello.service;

import spd.trello.domain.Attachment;
import spd.trello.repository.IRepository;

public class AttachmentService extends ServiceLayer<Attachment> {
    public AttachmentService(IRepository<Attachment> repository) {
        super(repository);
    }
}
