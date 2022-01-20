package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.*;
import spd.trello.repository.*;
import spd.trello.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckListTest extends BaseTest{

    private CheckListService service;
    private CheckList checkList;

    public CheckListTest(){
        service=new CheckListService(new CheckListDAO());
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
        checkList = new CheckList();
        checkList.setName("CheckList");
        checkList.setCardId(card.getId());
        checkList.setCreatedBy("klymovska.elina@gmail.com");
        checkList.setUpdatedBy("myfeatureknowlange@gmail.com");
        checkList.setCreatedDate(LocalDateTime.now());
        checkList.setUpdatedDate(LocalDateTime.now());
    }

    @Test
    public void successCreate() {
        CheckList checkList1 = service.create(checkList);
        assertNotNull(checkList1);
        assertAll(
                () -> assertNotNull(checkList1.getId()),
                () -> assertEquals("CheckList", checkList1.getName()),
                () -> assertEquals("klymovska.elina@gmail.com", checkList1.getCreatedBy()),
                () -> assertEquals("myfeatureknowlange@gmail.com", checkList1.getUpdatedBy()),
                () -> assertNotNull(checkList1.getUpdatedDate()),
                () -> assertNotNull(checkList1.getCreatedDate())
        );
    }

    @Test
    public void createFailure() {
        CheckList checkList1 = new CheckList();
        checkList1.setName(null);
        assertThrows(Exception.class, () -> new CheckListDAO().save(checkList1));
    }

    @Test
    public void testFindById() {
        UUID id = service.create(checkList).getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create(checkList).getId();
        CheckList checkList1 = new CheckList();
        checkList1.setName("TestCheckList");
        checkList1.setCreatedBy("klymovska.elina@gmail.com");
        checkList1.setCreatedDate(LocalDateTime.now());
        checkList1.setUpdatedBy("-");
        checkList1.setUpdatedDate(LocalDateTime.now());
        UUID id1 = service.create(checkList1).getId();
        List<CheckList> all = service.getAll();
        assertNotNull(all);
        assertAll(
                () -> assertEquals(service.readById(id), all.stream().filter(w -> w.getId().equals(id)).findAny().get()),
                () -> assertEquals(service.readById(id1), all.stream().filter(w -> w.getId().equals(id1)).findFirst().orElse(null)),
                () -> assertEquals(null, all.stream().filter(w -> w.getId().equals(UUID.randomUUID())).findAny().orElse(null))
        );
    }

    @Test
    public void successUpdate() {
        CheckList initialChecklist = service.create(checkList);
        CheckList checkList1 = new CheckList();
        checkList1.setName("TestCheckList");
        checkList1.setUpdatedBy("testing@gmail.com");
        checkList1.setCreatedBy(initialChecklist.getCreatedBy());
        checkList1.setCreatedDate(initialChecklist.getCreatedDate());
        checkList1.setUpdatedDate(LocalDateTime.now());
        service.update(initialChecklist.getId(), checkList1);
        assertNotNull(checkList1);
        assertAll(
                () -> assertEquals("TestCheckList", checkList1.getName()),
                () -> assertEquals("klymovska.elina@gmail.com", checkList1.getCreatedBy()),
                () -> assertEquals("testing@gmail.com", checkList1.getUpdatedBy()),
                () -> assertNotNull(checkList1.getUpdatedDate()),
                () -> assertNotNull(checkList1.getCreatedDate())
        );
    }

    @Test
    public void updateFailure() {
        CheckList initialCheckList = service.create(checkList);
        CheckList checkList1 = new CheckList();
        checkList1.setName(null);
        assertThrows(Exception.class, () -> service.update(initialCheckList.getId(), checkList1));
    }

    @Test
    public void successDelete() {
        UUID id = service.create(checkList).getId();
        assertEquals(service.readById(id), service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }
}
