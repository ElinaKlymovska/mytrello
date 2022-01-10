package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.Label;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class LabelDAO implements IRepository<Label> {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public LabelDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
        this.dataSource = dataSource;
    }

    public List<Label> getAll() {
        return jdbcTemplate.query("SELECT * FROM label", new BeanPropertyRowMapper<>(Label.class));
    }

    public Label getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM label WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Label.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Label label) {

        jdbcTemplate.update("INSERT INTO label(id,name,color_id)" +
                " VALUES(?,?,?)", label.getId(), label.getName(), label.getColor().getId());
    }

    public void update(UUID id, Label updatedLabel) {
        jdbcTemplate.update("UPDATE label SET name=?, color_id=? WHERE id=?", updatedLabel.getName(),
                updatedLabel.getColor().getId(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM label WHERE id=?", id);
    }
}
