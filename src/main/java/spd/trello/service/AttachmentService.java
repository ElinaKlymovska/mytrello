package spd.trello.service;

import spd.trello.domain.Attachment;
import spd.trello.repository.IRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AttachmentService extends ServiceLayer<Attachment> {

    public AttachmentService(IRepository<Attachment> repository) {
        super(repository);
    }

    @Override
    public Attachment readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public Attachment create() {
        Attachment attachment = new Attachment();
        attachment.setName("Attachment");
        attachment.setLink("link");
        attachment.setFile(new File("resources/SomeFile"));
        attachment.setCreatedBy("klymovska.elina@gmail.com");
        attachment.setCreatedDate(LocalDateTime.now());
        attachment.setUpdatedBy("-");
        attachment.setUpdatedDate(LocalDateTime.now());
        repository.save(attachment);
        return attachment;
    }

    @Override
    public void update(UUID id, Attachment object) {
        repository.update(id, object);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Attachment> getAll() {
        return repository.getAll();
    }
}
