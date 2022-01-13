package spd.trello.service;

import spd.trello.domain.Card;
import spd.trello.repository.IRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CardService extends ServiceLayer<Card> {


    public CardService(IRepository<Card> repository) {
        super(repository);
    }

    @Override
    public Card readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public Card create(Card card) {
        return repository.save(card);
    }

    @Override
    public void update(UUID id,Card updatedCard) {
        repository.update(id, updatedCard);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Card> getAll() {
        return repository.getAll();
    }
}
