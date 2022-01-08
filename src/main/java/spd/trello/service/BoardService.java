package spd.trello.service;

import spd.trello.domain.Board;
import spd.trello.domain.BoardVisibility;
import spd.trello.repository.IRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BoardService extends ServiceLayer<Board> {

    public BoardService(IRepository<Board> repository) {
        super(repository);
    }

    @Override
    public Board readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public Board create() {
        Board board = new Board();
        board.setName("Board");
        board.setDescription("Board for my future");
        board.setVisibility(BoardVisibility.PUBLIC);
        board.setArchived(false);
        board.setCreatedBy("klymovska.elina@gmail.com");
        board.setUpdatedBy("myfeatureknowlange@gmail.com");
        board.setCreatedDate(LocalDateTime.now());
        board.setUpdatedDate(LocalDateTime.now());
        repository.save(board);
        return board;
    }

    @Override
    public void update(UUID id, Board updatedBoard) {
        repository.update(id, updatedBoard);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Board> getAll() {
        return repository.getAll();
    }

}
