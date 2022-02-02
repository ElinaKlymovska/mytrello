package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.User;
import spd.trello.domain.Workspace;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;
@Component
public class UserDAO implements IRepository<User>{
    private final JdbcTemplate jdbcTemplate;

    public UserDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?",new BeanPropertyRowMapper<>(User.class),id)
                .stream().findFirst().orElse(null);
    }

    public User save(User user) {

        jdbcTemplate.update("INSERT INTO users(id,first_name,last_name,email,time_zone)" +
                        " VALUES(?,?,?,?,?)", user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getTimeZone().toZoneId().toString());
        return user;
    }

    public User update(UUID id, User user) {
        jdbcTemplate.update("UPDATE users SET first_name=?, last_name=?, email=?,time_zone=? WHERE id=?",
                user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getTimeZone().toZoneId().toString(), id);
        return user;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }
}
