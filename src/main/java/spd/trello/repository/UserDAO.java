package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
        this.dataSource = dataSource;
    }

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class));
    }

    public User getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM user WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findFirst().orElse(null);
    }

    public void save(User user) {

        jdbcTemplate.update("INSERT INTO user(id,first_name,last_name,email,time_zone)" +
                        " VALUES(?,?,?,?,?)", user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getTimeZone());
    }

    public void update(UUID id, User user) {
        jdbcTemplate.update("UPDATE user SET first_name=?, last_name=?, email=?,time_zone=? WHERE id=?",
                user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getTimeZone(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }
}
