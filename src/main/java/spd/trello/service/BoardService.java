package spd.trello.service;

import spd.trello.domain.Board;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class BoardService extends ServiceLayer<Board> {

    public BoardService(IRepository<Board> repository) {
        super(repository);
    }

}
