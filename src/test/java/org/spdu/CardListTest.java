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

public class CardListTest extends BaseTest{
    private final CardListService service;
    private CardList cardList;
    private Board testBoard;

    public CardListTest() {
        service = new CardListService(new CardListDAO());
        Workspace workspace = new Workspace();
        workspace.setName("FirstWorkspace");
        workspace.setDescription("My lovely project about jdbc,flyway,database");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedDate(LocalDateTime.now());
        new WorkspaceService(WorkspaceDAO.getInstance()).create(workspace);
        testBoard = new Board();
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
    }

    @Test
    public void successCreate() {
        CardList cardList = service.create(this.cardList);
        assertAll(
                () -> assertNotNull(cardList),
                () -> assertNotNull(cardList.getId()),
                () -> assertEquals("cardList", cardList.getName()),
                () -> assertEquals(testBoard.getId(), cardList.getBoardId()),
                () -> assertEquals(false, cardList.getArchived()),
                () -> assertEquals("klymovska.elina@gmail.com", cardList.getCreatedBy()),
                () -> assertEquals("myfeatureknowlange@gmail.com", cardList.getUpdatedBy()),
                () -> assertNotNull(cardList.getUpdatedDate()),
                () -> assertNotNull(cardList.getCreatedDate())
        );
    }

    @Test
    public void createFailure() {
        CardList cardList = new CardList();
        cardList.setName(null);
        assertThrows(Exception.class, () -> new CardListDAO().save(cardList));
    }

    @Test
    public void testFindById() {
        UUID id = service.create(cardList).getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create(cardList).getId();
        CardList testCard = new CardList();
        testCard.setName("cardList123");
        testCard.setArchived(true);
        testCard.setBoardId(cardList.getBoardId());
        testCard.setCreatedBy("klymovska.elina@gmail.com");
        testCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        testCard.setCreatedDate(LocalDateTime.now());
        testCard.setUpdatedDate(LocalDateTime.now());
        UUID id1 = service.create(testCard).getId();
        List<CardList> all = service.getAll();
        assertNotNull(all);
        assertAll(
                () -> assertEquals(service.readById(id).getId(), all.stream().filter(w -> w.getId().equals(id)).findAny().get().getId()),
                () -> assertEquals(service.readById(id1).getId(), all.stream().filter(w -> w.getId().equals(id1)).findFirst().orElse(null).getId()),
                () -> assertEquals(null, all.stream().filter(w -> w.getId().equals(UUID.randomUUID())).findAny().orElse(null))
        );
    }

    @Test
    public void successUpdate() {
        CardList initialCard = service.create(cardList);
        CardList testCardList = new CardList();
        testCardList.setName("TestCardList");
        testCardList.setArchived(true);
        testCardList.setBoardId(cardList.getBoardId());
        testCardList.setUpdatedBy("testing@gmail.com");
        testCardList.setCreatedBy(initialCard.getCreatedBy());
        testCardList.setCreatedDate(initialCard.getCreatedDate());
        testCardList.setUpdatedDate(LocalDateTime.now());
        service.update(initialCard.getId(), testCardList);
        assertNotNull(testCardList);
        assertAll(
                () -> assertEquals("TestCardList", testCardList.getName()),
                () -> assertEquals(testBoard.getId(), testCardList.getBoardId()),
                () -> assertEquals(true, testCardList.getArchived()),
                () -> assertEquals("klymovska.elina@gmail.com", testCardList.getCreatedBy()),
                () -> assertEquals("testing@gmail.com", testCardList.getUpdatedBy()),
                () -> assertNotNull(testCardList.getUpdatedDate()),
                () -> assertNotNull(testCardList.getCreatedDate())
        );
    }

    @Test
    public void updateFailure() {
        CardList initialCard = service.create(cardList);
        CardList testCardList = new CardList();
        testCardList.setName(null);
        assertThrows(Exception.class, () -> service.update(initialCard.getId(), testCardList));
    }

    @Test
    public void successDelete() {
        UUID id = service.create(cardList).getId();
        assertEquals(id, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null).getId());
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }
}
