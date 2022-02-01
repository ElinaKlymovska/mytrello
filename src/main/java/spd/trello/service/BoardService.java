package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Board;
import spd.trello.repository.BoardDAO;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;
@Service
public class BoardService extends ServiceLayer<Board, BoardDAO> {
    @Autowired
    public BoardService(BoardDAO repository) {
        super(repository);
    }

}
