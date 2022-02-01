package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spd.trello.domain.Label;

import java.util.List;
import java.util.UUID;
@Component
public class LabelDAO implements IRepository<Label> {

    private final JdbcTemplate jdbcTemplate;

    public LabelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Label> getAll() {
        return jdbcTemplate.query("SELECT * FROM label", new BeanPropertyRowMapper<>(Label.class));
    }

    public Label getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM label WHERE id=?",
                        new BeanPropertyRowMapper<>(Label.class),id)
                .stream().findFirst().orElse(null);
    }

    public Label save(Label label) {

        jdbcTemplate.update("INSERT INTO label(id,name,color_id)" +
                " VALUES(?,?,?)", label.getId(), label.getName(), label.getColor().getId());
        return label;
    }

    public Label update(UUID id, Label updatedLabel) {
        jdbcTemplate.update("UPDATE label SET name=?, color_id=? WHERE id=?", updatedLabel.getName(),
                updatedLabel.getColor().getId(), id);
        return updatedLabel;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM label WHERE id=?", id);
    }
}
