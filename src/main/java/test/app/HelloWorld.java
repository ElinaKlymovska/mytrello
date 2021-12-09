package test.app;

import spd.trello.domain.Board;

public class HelloWorld {
    public static void main(String[] args) {
        Board board = new Board();
        board.setName("Bob");
        System.out.println("Hello World!");
    }
}