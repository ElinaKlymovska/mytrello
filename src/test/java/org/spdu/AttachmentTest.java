package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.*;
import spd.trello.repository.*;
import spd.trello.service.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttachmentTest extends BaseTest {

    private final AttachmentService service;
    private Attachment createdAttachment;

    public AttachmentTest() {
        service = new AttachmentService(new AttachmentDAO());
        User user = new User();
        user.setFirstName("Sam");
        user.setLastName("Li");
        user.setEmail("creativity@gmail.com");
        user.setTimeZone(LocalDateTime.now());
        new UserService(new UserDAO()).create(user);
        Member member = new Member();
        member.setRole(Role.GUEST);
        member.setUser(user);
        new MemberService(new MemberDAO()).create(member);
        Reminder reminder = new Reminder();
        reminder.setStart(LocalDateTime.now());
        reminder.setEnd(LocalDateTime.now());
        reminder.setRemindOn(LocalDateTime.now());
        reminder.setActtive(false);
        reminder.setCreatedBy("klymovska.elina@gmail.com");
        reminder.setUpdatedBy("myfeatureknowlange@gmail.com");
        reminder.setCreatedDate(LocalDateTime.now());
        reminder.setUpdatedDate(LocalDateTime.now());
        new ReminderService(new ReminderDAO()).create(reminder);
        Workspace workspace = new Workspace();
        workspace.setName("FirstWorkspace");
        workspace.setDescription("My lovely project about jdbc,flyway,database");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedDate(LocalDateTime.now());
        new WorkspaceService(WorkspaceDAO.getInstance()).create(workspace);
        Board testBoard = new Board();
        testBoard.setName("Board");
        testBoard.setDescription("Board for my future");
        testBoard.setVisibility(BoardVisibility.PUBLIC);
        testBoard.setWorkspaceId(workspace.getId());
        testBoard.setArchived(false);
        testBoard.setCreatedBy("klymovska.elina@gmail.com");
        testBoard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testBoard.setCreatedDate(LocalDateTime.now());
        testBoard.setUpdatedDate(LocalDateTime.now());
        new BoardService(new BoardDAO()).create(testBoard);
        CardList cardList = new CardList();
        cardList.setName("cardList");
        cardList.setArchived(false);
        cardList.setBoardId(testBoard.getId());
        cardList.setCreatedBy("klymovska.elina@gmail.com");
        cardList.setUpdatedBy("myfeatureknowlange@gmail.com");
        cardList.setCreatedDate(LocalDateTime.now());
        cardList.setUpdatedDate(LocalDateTime.now());
        new CardListService(new CardListDAO()).create(cardList);
        Card card = new Card();
        card.setName("name");
        card.setDescription("QWERTY");
        card.setArchived(true);
        card.setReminder(reminder);
        card.setCardList(cardList);
        card.setCreatedBy("klymovska.elina@gmail.com");
        card.setUpdatedBy("myfeatureknowlange@gmail.com");
        card.setCreatedDate(LocalDateTime.now());
        card.setUpdatedDate(LocalDateTime.now());
        new CardService(new CardDAO()).create(card);
        Comment comment =new Comment();
        comment.setMember(member);
        comment.setLocalDateTime(LocalDateTime.now());
        comment.setCardId(card.getId());
        comment.setText("Comment");
        comment.setCreatedBy("klymovska.elina@gmail.com");
        comment.setUpdatedBy("myfeatureknowlange@gmail.com");
        comment.setCreatedDate(LocalDateTime.now());
        comment.setUpdatedDate(LocalDateTime.now());
        new CommentService(new CommentDAO()).create(comment);
        createdAttachment = new Attachment();
        createdAttachment.setName("Attachment");
        createdAttachment.setLink("link");
        createdAttachment.setFile(new File("resources/SomeFile"));
        createdAttachment.setCommentId(comment.getId());
        createdAttachment.setCardId(comment.getCardId());
        createdAttachment.setCreatedBy("klymovska.elina@gmail.com");
        createdAttachment.setCreatedDate(LocalDateTime.now());
        createdAttachment.setUpdatedBy("-");
        createdAttachment.setUpdatedDate(LocalDateTime.now());
    }

    @Test
    public void successCreate() {
        Attachment attachment = service.create(createdAttachment);
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
        assertThrows(Exception.class, () -> new AttachmentDAO().save(attachment));
    }

    @Test
    public void testFindById() {
        UUID id = service.create(createdAttachment).getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create(createdAttachment).getId();
        Attachment testAttachment = new Attachment();
        testAttachment.setName("Attachment2");
        testAttachment.setLink("link2");
        testAttachment.setFile(new File("resources/SomeFile2"));
        testAttachment.setCreatedBy("klymovska.elina@gmail.com");
        testAttachment.setCreatedDate(LocalDateTime.now());
        testAttachment.setUpdatedBy("-");
        testAttachment.setUpdatedDate(LocalDateTime.now());
        UUID id1 = service.create(testAttachment).getId();
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
        Attachment initialAttachment = service.create(createdAttachment);

        Attachment attachment = new Attachment();
        attachment.setName("TestAttachment");
        attachment.setFile(new File("resources\\SomeFile2"));
        attachment.setUpdatedBy("testing@gmail.com");
        attachment.setCreatedBy(initialAttachment.getCreatedBy());
        attachment.setCreatedDate(initialAttachment.getCreatedDate());
        attachment.setUpdatedDate(LocalDateTime.now());
        service.update(initialAttachment.getId(), attachment);
        assertNotNull(attachment);
        assertAll(
                () -> assertEquals("TestAttachment", attachment.getName()),
                () -> assertEquals(new File("resources\\SomeFile2"), attachment.getFile()),
                () -> assertEquals("klymovska.elina@gmail.com", attachment.getCreatedBy()),
                () -> assertEquals("testing@gmail.com", attachment.getUpdatedBy()),
                () -> assertNotNull(attachment.getUpdatedDate()),
                () -> assertNotNull(attachment.getCreatedDate())
        );
    }

    @Test
    public void updateFailure() {
        Attachment initialAttachment = service.create(createdAttachment);
        Attachment attachment = new Attachment();
        attachment.setName(null);
        assertThrows(Exception.class, () -> service.update(initialAttachment.getId(), attachment));
    }

    @Test
    public void successDelete() {
        UUID id = service.create(createdAttachment).getId();
        assertEquals(service.readById(id), service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }
}
