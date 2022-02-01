package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.*;
import spd.trello.repository.BoardDAO;
import spd.trello.repository.CardListDAO;
import spd.trello.repository.CheckableItemDAO;
import spd.trello.repository.WorkspaceDAO;
import spd.trello.service.BoardService;
import spd.trello.service.CardListService;
import spd.trello.service.CheckAbleItemService;
import spd.trello.service.WorkspaceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckAbleItemServiceTest extends BaseTest{

    private CheckAbleItemService service;
    private CheckableItem checkableItem;
    private CardList cardList;

    public CheckAbleItemServiceTest() {
        service = new CheckAbleItemService(new CheckableItemDAO());
        Workspace workspace = new Workspace();
        workspace.setName("FirstWorkspace");
        workspace.setDescription("My lovely project about jdbc,flyway,database");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedDate(LocalDateTime.now());
        new WorkspaceService(new WorkspaceDAO()).create(workspace);
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
        cardList = new CardList();
        cardList.setName("cardList");
        cardList.setArchived(false);
        cardList.setBoardId(testBoard.getId());
        cardList.setCreatedBy("klymovska.elina@gmail.com");
        cardList.setUpdatedBy("myfeatureknowlange@gmail.com");
        cardList.setCreatedDate(LocalDateTime.now());
        cardList.setUpdatedDate(LocalDateTime.now());
        new CardListService(new CardListDAO()).create(cardList);
        checkableItem=new CheckableItem();
        checkableItem.setName("CheckAbleItem");
        checkableItem.setCheckedSwitcher(false);
        checkableItem.setCardListId(cardList.getId());
    }

    @Test
    public void successCreate() {
        CheckableItem checkableItem = service.create(this.checkableItem);
        assertAll(
                () -> assertNotNull(checkableItem),
                () -> assertNotNull(checkableItem.getId()),
                () -> assertEquals("CheckAbleItem", checkableItem.getName()),
                () -> assertEquals(cardList.getId(), checkableItem.getCardListId()),
                () -> assertEquals(false, checkableItem.getCheckedSwitcher())
        );
    }

    @Test
    public void createFailure() {
        CheckableItem checkableItem = new CheckableItem();
        checkableItem.setName(null);
        assertThrows(Exception.class, () -> new CheckableItemDAO().save(checkableItem));
    }

    @Test
    public void testFindById() {
        UUID id = service.create(checkableItem).getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create(checkableItem).getId();
        CheckableItem testCard = new CheckableItem();
        testCard.setName("CheckableItem123");
        testCard.setCheckedSwitcher(true);
        testCard.setCardListId(cardList.getId());
        UUID id1 = service.create(testCard).getId();
        List<CheckableItem> all = service.getAll();
        assertNotNull(all);
        assertAll(
                () -> assertEquals(service.readById(id).getId(), all.stream().filter(w -> w.getId().equals(id)).findAny().get().getId()),
                () -> assertEquals(service.readById(id1).getId(), all.stream().filter(w -> w.getId().equals(id1)).findFirst().orElse(null).getId()),
                () -> assertEquals(null, all.stream().filter(w -> w.getId().equals(UUID.randomUUID())).findAny().orElse(null))
        );
    }

    @Test
    public void successUpdate() {
        CheckableItem initialCard = service.create(checkableItem);
        CheckableItem checkableItem1 = new CheckableItem();
        checkableItem1.setName("TestCheckableItem");
        checkableItem1.setCheckedSwitcher(true);
        checkableItem1.setCardListId(cardList.getId());
        service.update(initialCard.getId(), checkableItem1);
        assertNotNull(checkableItem1);
        assertAll(
                () -> assertEquals("TestCheckableItem", checkableItem1.getName()),
                () -> assertEquals(cardList.getId(), checkableItem1.getCardListId()),
                () -> assertEquals(true, checkableItem1.getCheckedSwitcher())
        );
    }

    @Test
    public void updateFailure() {
        CheckableItem initialCard = service.create(checkableItem);
        CheckableItem testCardList = new CheckableItem();
        testCardList.setName(null);
        assertThrows(Exception.class, () -> service.update(initialCard.getId(), testCardList));
    }

    @Test
    public void successDelete() {
        UUID id = service.create(checkableItem).getId();
        assertEquals(id, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null).getId());
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }
}
