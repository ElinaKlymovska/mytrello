package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.CheckableItem;
import spd.trello.config.DataBaseConfiguration;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class CheckableItemDAO implements IRepository<CheckableItem>{

    private final JdbcTemplate jdbcTemplate;

    public CheckableItemDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<CheckableItem> getAll() {
        return jdbcTemplate.query("SELECT * FROM check_able_item",
                new BeanPropertyRowMapper<>(CheckableItem.class));
    }

    public CheckableItem getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM check_able_item WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(CheckableItem.class))
                .stream().findFirst().orElse(null);

    }

    public CheckableItem save(CheckableItem checkableItem) {
        jdbcTemplate.update("INSERT INTO check_able_item(id,name,checked) VALUES(?,?,?)",
                checkableItem.getId(), checkableItem.getName(), checkableItem.getCheckedSwitcher());
        return checkableItem;
    }

    public CheckableItem update(UUID id, CheckableItem checkableItem) {
        jdbcTemplate.update("UPDATE check_able_item SET name=?, checked=?, WHERE id=?",
                checkableItem.getName(), checkableItem.getCheckedSwitcher(), id);
        return checkableItem;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM check_able_item WHERE id=?", id);
    }
}
