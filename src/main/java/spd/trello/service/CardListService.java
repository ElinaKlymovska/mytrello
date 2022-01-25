package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.CardList;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;
@Service
public class CardListService extends ServiceLayer<CardList>{
    public CardListService(IRepository<CardList> repository) {
        super(repository);
    }
}
