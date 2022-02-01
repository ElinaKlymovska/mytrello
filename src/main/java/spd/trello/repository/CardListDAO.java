package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spd.trello.domain.CardList;

import java.util.List;
import java.util.UUID;
@Component
public class CardListDAO implements IRepository<CardList> {

    private final JdbcTemplate jdbcTemplate;

    public CardListDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CardList> getAll() {
        return jdbcTemplate.query("SELECT * FROM cardlist", new BeanPropertyRowMapper<>(CardList.class));
    }

    public CardList getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM cardlist WHERE id=?",
                        new BeanPropertyRowMapper<>(CardList.class),id)
                .stream().findFirst().orElse(null);
    }

    public CardList save(CardList cardList) {
        jdbcTemplate.update("INSERT INTO cardlist(id,name,archived,board_id," +
                        "created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?)", cardList.getId(), cardList.getName(), cardList.getArchived(),
                cardList.getBoardId(), cardList.getCreatedBy(), cardList.getUpdatedBy(), cardList.getCreatedDate(), cardList.getUpdatedDate());
        return cardList;
    }

    public CardList update(UUID id, CardList updetedCardList) {
        jdbcTemplate.update("UPDATE cardlist SET name=?, archived=?, board_id=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updetedCardList.getName(),
                updetedCardList.getArchived(), updetedCardList.getBoardId(),
                updetedCardList.getCreatedBy(), updetedCardList.getUpdatedBy(), updetedCardList.getCreatedDate(), updetedCardList.getUpdatedDate(), id);
        return updetedCardList;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM cardlist WHERE id=?", id);
    }
}
