package test.app;

import spd.trello.domain.Board;
import spd.trello.domain.Card;
import spd.trello.service.BoardService;
import spd.trello.service.CardService;

public class HelloWorld {
    public static void main(String[] args) {
        new BoardService().print(new BoardService().create());
        CardService cardService = new CardService();
        Card card =cardService.create();
        card.setName("new Name");
        cardService.update(0,card);
        cardService.print(card);
    }
}