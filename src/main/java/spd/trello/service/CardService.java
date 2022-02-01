package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Card;
import spd.trello.repository.CardDAO;
import spd.trello.repository.IRepository;

@Service
public class CardService extends ServiceLayer<Card, CardDAO> {
    @Autowired
    public CardService(CardDAO repository) {
        super(repository);
    }
}
