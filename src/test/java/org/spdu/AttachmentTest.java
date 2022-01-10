package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.Attachment;
import spd.trello.repository.AttachmentDAO;
import spd.trello.service.AttachmentService;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttachmentTest extends BaseTest {

    private final AttachmentService service;

    public AttachmentTest() {
        service = new AttachmentService(new AttachmentDAO(dataSource));
    }

    @Test
    public void successCreate() {
        Attachment attachment = service.create();
        assertNotNull(attachment);
        assertAll(
                () -> assertNotNull(attachment.getId()),
                () -> assertEquals("Attachment", attachment.getName()),
                () -> assertEquals(new File("resources\\SomeFile"), attachment.getFile()),
                () -> assertEquals("klymovska.elina@gmail.com", attachment.getCreatedBy()),
                () -> assertEquals("-", attachment.getUpdatedBy()),
                () -> assertNotNull(attachment.getUpdatedDate()),
                () -> assertNotNull(attachment.getCreatedDate())
        );
    }

    @Test
    public void createFailure() {
        Attachment attachment = new Attachment();
        attachment.setName(null);
        assertThrows(Exception.class, () -> new AttachmentDAO(dataSource).save(attachment));
    }

    @Test
    public void testFindById() {
        UUID id = service.create().getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create().getId();
        UUID id1 = service.create().getId();
        List<Attachment> all = service.getAll();
        assertNotNull(all);
        assertAll(
                () -> assertEquals(service.readById(id), all.stream().filter(w -> w.getId().equals(id)).findAny().get()),
                () -> assertEquals(service.readById(id1), all.stream().filter(w -> w.getId().equals(id1)).findFirst().orElse(null)),
                () -> assertEquals(null, all.stream().filter(w -> w.getId().equals(UUID.randomUUID())).findAny().orElse(null))
        );
    }

    @Test
    public void successUpdate() {
        Attachment initialAttachment = service.create();

        Attachment attachment = new Attachment();
        attachment.setName("TestAttachment");
        attachment.setFile(initialAttachment.getFile());
        attachment.setUpdatedBy("testing@gmail.com");
        attachment.setCreatedBy(initialAttachment.getCreatedBy());
        attachment.setCreatedDate(initialAttachment.getCreatedDate());
        attachment.setUpdatedDate(LocalDateTime.now());
        service.update(initialAttachment.getId(), attachment);
        assertNotNull(attachment);
        assertAll(
                () -> assertEquals("TestAttachment", attachment.getName()),
                () -> assertEquals(new File("resources\\SomeFile"), attachment.getFile()),
                () -> assertEquals("klymovska.elina@gmail.com", attachment.getCreatedBy()),
                () -> assertEquals("testing@gmail.com", attachment.getUpdatedBy()),
                () -> assertNotNull(attachment.getUpdatedDate()),
                () -> assertNotNull(attachment.getCreatedDate())
        );
    }

    @Test
    public void updateFailure() {
        Attachment initialAttachment = service.create();
        Attachment attachment = new Attachment();
        attachment.setName(null);
        assertThrows(Exception.class, () -> service.update(initialAttachment.getId(), attachment));
    }

    @Test
    public void successDelete() {
        UUID id = service.create().getId();
        assertEquals(service.readById(id), service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }
}
