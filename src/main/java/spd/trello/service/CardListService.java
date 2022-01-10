package spd.trello.service;

import spd.trello.domain.CardList;
import spd.trello.repository.IRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardListService extends ServiceLayer<CardList>{
    public CardListService(IRepository<CardList> repository) {
        super(repository);
    }

    @Override
    public CardList readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public CardList create() {
        CardList cardList = new CardList();
        cardList.setName("cardList");
        cardList.setCards(new ArrayList<>());
        cardList.setArchived(false);
        cardList.setCreatedBy("klymovska.elina@gmail.com");
        cardList.setUpdatedBy("myfeatureknowlange@gmail.com");
        cardList.setCreatedDate(LocalDateTime.now());
        cardList.setUpdatedDate(LocalDateTime.now());
        repository.save(cardList);
        return cardList;
    }

    @Override
    public void update(UUID id, CardList cardList) {
        repository.update(id, cardList);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<CardList> getAll() {
        return repository.getAll();
    }
}
