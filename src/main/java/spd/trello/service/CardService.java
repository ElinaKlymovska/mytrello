package spd.trello.service;

import spd.trello.domain.Card;
import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.CardDAO;
import spd.trello.repository.WorkspaceDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CardService extends ServiceLayer<Card> {
    private final CardDAO cardDAO;

    public CardService() {
        cardDAO =new CardDAO();
    }

    @Override
    public Card readById(UUID id) {
        return cardDAO.getById(id);
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
        cardDAO.save(card);
        return card;
    }

    @Override
    public void update(UUID id,Card updatedCard) {
        updatedCard.setName("NewWorkspace");
        updatedCard.setDescription("new challenges");
        updatedCard.setArchived(true);
        updatedCard.setCreatedBy("klymovska.elina@gmail.com");
        updatedCard.setUpdatedBy("myfeatureknowlange@gmail.com");
        updatedCard.setCreatedDate(LocalDateTime.now());
        updatedCard.setUpdatedDate(LocalDateTime.now());
        cardDAO.update(id, updatedCard);
    }

    @Override
    public void delete(UUID id) {
        cardDAO.delete(id);
    }

    @Override
    public List<Card> getAll() {
        return cardDAO.getAll();
    }
}
