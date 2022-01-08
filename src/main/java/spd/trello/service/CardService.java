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
    public Card create() {
        Card card = new Card();
        card.setName("FirstCard");
        card.setDescription("My lovely project about with card");
        card.setArchived(false);
        card.setCreatedBy("klymovska.elina@gmail.com");
        card.setUpdatedBy("myfeatureknowlange@gmail.com");
        card.setCreatedDate(LocalDateTime.now());
        card.setUpdatedDate(LocalDateTime.now());
        repository.save(card);
        return card;
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
