package spd.trello.service;

import spd.trello.domain.CardList;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class CardListService extends ServiceLayer<CardList>{
    public CardListService(IRepository<CardList> repository) {
        super(repository);
    }
}
