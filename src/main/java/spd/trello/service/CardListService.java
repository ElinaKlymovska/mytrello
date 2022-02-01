package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.CardList;
import spd.trello.repository.CardListDAO;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;
@Service
public class CardListService extends ServiceLayer<CardList, CardListDAO>{
    @Autowired
    public CardListService(CardListDAO repository) {
        super(repository);
    }
}
