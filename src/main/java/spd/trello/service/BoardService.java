package spd.trello.service;

import spd.trello.domain.Board;
import spd.trello.repository.IRepository;

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
    public Board create(Board board) {
        return repository.save(board);
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
