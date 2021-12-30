package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.CardList;
import test.app.DataBaseConfiguration;

import java.util.List;
import java.util.UUID;

public class CardListDAO {

    private final JdbcTemplate jdbcTemplate;

    public CardListDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<CardList> getAll() {
        return jdbcTemplate.query("SELECT * FROM cardlist", new BeanPropertyRowMapper<>(CardList.class));
    }

    public CardList getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM cardlist WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(CardList.class))
                .stream().findFirst().orElse(null);
    }

    public void save(CardList cardList) {
        jdbcTemplate.update("INSERT INTO cardlist(id,name,archived,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?)", cardList.getId(), cardList.getName(), cardList.getArchived(),
                cardList.getCreatedBy(), cardList.getUpdatedBy(), cardList.getCreatedDate(), cardList.getUpdatedDate());
    }

    public void update(UUID id, CardList updetedCardList) {
        jdbcTemplate.update("UPDATE cardlist SET name=?, description=?, visibility=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updetedCardList.getName(),
                updetedCardList.getArchived(), updetedCardList.getCreatedBy(), updetedCardList.getUpdatedBy(), updetedCardList.getCreatedDate(), updetedCardList.getUpdatedDate(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM cardlist WHERE id=?", id);
    }
}
