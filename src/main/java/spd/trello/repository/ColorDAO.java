package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Color;
import test.app.DataBaseConfiguration;

import java.util.List;
import java.util.UUID;

public class ColorDAO {
    private JdbcTemplate jdbcTemplate;

    public ColorDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<Color> getAll() {
        return jdbcTemplate.query("SELECT * FROM checklist",
                new BeanPropertyRowMapper<>(Color.class));
    }

    public Color getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM color WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Color.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Color color) {
        jdbcTemplate.update("INSERT INTO color(id,color) VALUES(?,?)",
                color.getId(), color.getColor());
    }

    public void update(UUID id, Color updatedColor) {
        jdbcTemplate.update("UPDATE color SET color=?, WHERE id=?",updatedColor.getColor(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM color WHERE id=?", id);
    }
}
