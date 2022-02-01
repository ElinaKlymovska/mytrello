package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spd.trello.domain.Card;

import java.util.List;
import java.util.UUID;
@Component
public class CardDAO implements IRepository<Card> {
    private final JdbcTemplate jdbcTemplate;

    public CardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Card> getAll() {
        return jdbcTemplate.query("SELECT * FROM card", new BeanPropertyRowMapper<>(Card.class));
    }

    public Card getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM card WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Card.class))
                .stream().findFirst().orElse(null);
    }

    public Card save(Card card) {
        jdbcTemplate.update("INSERT INTO card(id,name,description,archived,reminder_id, " +
                        "cardlist_id, created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?,?,?)", card.getId(), card.getName(), card.getDescription(), card.getArchived(),
                card.getReminder().getId(), card.getCardList().getId(),
                card.getCreatedBy(), card.getUpdatedBy(), card.getCreatedDate(), card.getUpdatedDate());
        return card;
    }

    public Card update(UUID id, Card updatedCard) {
        jdbcTemplate.update("UPDATE card SET name=?, description=?, archived=?,reminder_id=?, cardlist_id=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updatedCard.getName(),
                updatedCard.getDescription(), updatedCard.getArchived(), updatedCard.getReminder().getId(),
                updatedCard.getCardList().getId(), updatedCard.getCreatedBy(),
                updatedCard.getUpdatedBy(), updatedCard.getCreatedDate(), updatedCard.getUpdatedDate(), id);
        return updatedCard;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM card WHERE id=?", id);
    }
}
