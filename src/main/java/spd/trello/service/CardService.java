package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Card;
import spd.trello.repository.IRepository;

@Service
public class CardService extends ServiceLayer<Card> {
    public CardService(IRepository<Card> repository) {
        super(repository);
    }
}
