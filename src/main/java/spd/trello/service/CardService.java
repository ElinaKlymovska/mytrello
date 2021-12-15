package spd.trello.service;

import spd.trello.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class CardService extends ServiceLayer<Card> {
    private List<Card> storage = new ArrayList<>();


    @Override
    public Card create() {
        Card card = new Card();
        card.setName("Card1");
        storage.add(card);
        return card;
    }

    @Override
    public void update(int index, Card object) {
        Card oldCard = storage.get(index);
        oldCard.setName(object.getName());
    }
}
