package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Card;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.CheckableItem;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class CardDAO implements IRepository<Card>{
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public CardDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<Card> getAll() {
        return jdbcTemplate.query("SELECT * FROM card", new BeanPropertyRowMapper<>(Card.class));
    }

    public Card getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM card WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Card.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Card card) {
        jdbcTemplate.update("INSERT INTO card(id,name,description,archived,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?)", card.getId(), card.getName(), card.getDescription(), card.getArchived(),
                card.getCreatedBy(), card.getUpdatedBy(), card.getCreatedDate(), card.getUpdatedDate());
    }

    public void update(UUID id, Card updatedCard) {
        jdbcTemplate.update("UPDATE card SET name=?, description=?, archived=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updatedCard.getName(),
                updatedCard.getDescription(), updatedCard.getArchived(), updatedCard.getCreatedBy(),
                updatedCard.getUpdatedBy(), updatedCard.getCreatedDate(), updatedCard.getUpdatedDate(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM card WHERE id=?", id);
    }
}
