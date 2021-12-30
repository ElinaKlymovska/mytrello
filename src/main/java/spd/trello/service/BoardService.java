package spd.trello.service;

import spd.trello.domain.Board;
import spd.trello.domain.BoardVisibility;
import spd.trello.domain.Card;
import spd.trello.repository.BoardDAO;
import spd.trello.repository.CardDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BoardService extends ServiceLayer<Board> {
    private final BoardDAO boardDAO;

    public BoardService() {
        boardDAO = new BoardDAO();
    }

    @Override
    public Board readById(UUID id) {
        return boardDAO.getById(id);
    }

    @Override
    public Board create() {
        Board board = new Board();
        board.setName("FirstCard");
        board.setDescription("My lovely project about with card");
        board.setVisibility(BoardVisibility.PUBLIC);
        board.setArchived(false);
        board.setCreatedBy("klymovska.elina@gmail.com");
        board.setUpdatedBy("myfeatureknowlange@gmail.com");
        board.setCreatedDate(LocalDateTime.now());
        board.setUpdatedDate(LocalDateTime.now());
        boardDAO.save(board);
        return board;
    }

    @Override
    public void update(UUID id, Board updatedBoard) {
        boardDAO.update(id, updatedBoard);
    }

    @Override
    public void delete(UUID id) {
        boardDAO.delete(id);
    }

    @Override
    public List<Board> getAll() {
        return boardDAO.getAll();
    }

}
