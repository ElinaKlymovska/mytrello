package spd.trello.service;

import spd.trello.domain.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardService extends ServiceLayer<Board>{
    private List<Board> storage = new ArrayList<>();

    @Override
    public Board create() {
        Board board = new Board();
        board.setName("Board1");
        storage.add(board);
        return board;
    }

    @Override
    public void update(int index, Board object) {
        Board oldBoard = storage.get(index);
        oldBoard.setName(object.getName());
    }

}
