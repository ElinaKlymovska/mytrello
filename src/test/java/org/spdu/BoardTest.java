package org.spdu;

import org.junit.jupiter.api.Test;
import spd.trello.domain.Board;
import spd.trello.domain.BoardVisibility;
import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.BoardDAO;
import spd.trello.repository.WorkspaceDAO;
import spd.trello.service.BoardService;
import spd.trello.service.WorkspaceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest extends BaseTest {
    private final BoardService service;
    private Board testBoard;

    public BoardTest() {
        service = new BoardService(new BoardDAO());
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
    }

    @Test
    public void successCreate() {
        Board board = service.create(testBoard);
        assertNotNull(board);
        assertAll(
                () -> assertNotNull(board.getId()),
                () -> assertEquals("Board", board.getName()),
                () -> assertEquals("Board for my future", board.getDescription()),
                () -> assertEquals(BoardVisibility.PUBLIC, board.getVisibility()),
                () -> assertEquals(false, board.getArchived()),
                () -> assertEquals("klymovska.elina@gmail.com", board.getCreatedBy()),
                () -> assertEquals("myfeatureknowlange@gmail.com", board.getUpdatedBy()),
                () -> assertNotNull(board.getUpdatedDate()),
                () -> assertNotNull(board.getCreatedDate())
        );
    }

    @Test
    public void createFailure() {
        Board board = new Board();
        board.setName(null);
        assertThrows(Exception.class, () -> new BoardDAO().save(board));
    }

    @Test
    public void testFindById() {
        UUID id = service.create(testBoard).getId();
        assertEquals(id, service.readById(id).getId());
    }

    @Test
    public void testNoFindById() {
        UUID uuid = UUID.randomUUID();
        assertEquals(null, service.readById(uuid));
    }

    @Test
    public void testFindAll() {
        UUID id = service.create(testBoard).getId();
        Board testBoard2 = new Board();
        testBoard2.setName("Board2");
        testBoard2.setDescription("Board for my future2");
        testBoard2.setVisibility(BoardVisibility.PUBLIC);
        testBoard2.setWorkspaceId(testBoard.getWorkspaceId());
        testBoard2.setArchived(false);
        testBoard2.setCreatedBy("klymovska.elina@gmail.com");
        testBoard2.setUpdatedBy("myfeatureknowlange@gmail.com");
        testBoard2.setCreatedDate(LocalDateTime.now());
        testBoard2.setUpdatedDate(LocalDateTime.now());
        UUID id1 = service.create(testBoard2).getId();
        List<Board> all = service.getAll();
        assertNotNull(all);
        assertAll(
                () -> assertEquals(service.readById(id), all.stream().filter(w -> w.getId().equals(id)).findAny().get()),
                () -> assertEquals(service.readById(id1), all.stream().filter(w -> w.getId().equals(id1)).findFirst().orElse(null)),
                () -> assertEquals(null, all.stream().filter(w -> w.getId().equals(UUID.randomUUID())).findAny().orElse(null))
        );
    }

    @Test
    public void successUpdate() {
        Board initialBoard = service.create(testBoard);

        Board board = new Board();
        board.setName("TestBoard");
        board.setDescription("Testing update");
        board.setArchived(true);
        testBoard.setWorkspaceId(testBoard.getWorkspaceId());
        board.setUpdatedBy("testing@gmail.com");
        board.setCreatedBy(initialBoard.getCreatedBy());
        board.setCreatedDate(initialBoard.getCreatedDate());
        board.setUpdatedDate(LocalDateTime.now());
        service.update(initialBoard.getId(), board);
        assertNotNull(board);
        assertAll(
                () -> assertEquals("TestBoard", board.getName()),
                () -> assertEquals("Testing update", board.getDescription()),
                () -> assertEquals(true, board.getArchived()),
                () -> assertEquals("klymovska.elina@gmail.com", board.getCreatedBy()),
                () -> assertEquals("testing@gmail.com", board.getUpdatedBy()),
                () -> assertNotNull(board.getUpdatedDate()),
                () -> assertNotNull(board.getCreatedDate())
        );
    }

    @Test
    public void updateFailure() {
        Board initialBoard = service.create(testBoard);
        Board board = new Board();
        board.setName(null);
        board.setDescription(null);
        assertThrows(Exception.class, () -> service.update(initialBoard.getId(), board));
    }

    @Test
    public void successDelete() {
        UUID id = service.create(testBoard).getId();
        assertEquals(service.readById(id), service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
        service.delete(id);
        assertEquals(null, service.getAll().stream().filter(w -> w.getId().equals(id)).findAny().orElse(null));
    }

}
