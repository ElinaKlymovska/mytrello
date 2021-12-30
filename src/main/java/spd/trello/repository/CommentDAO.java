package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Comment;
import test.app.DataBaseConfiguration;

import java.util.List;
import java.util.UUID;

public class CommentDAO {
    private JdbcTemplate jdbcTemplate;

    public CommentDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<Comment> getAll() {
        return jdbcTemplate.query("SELECT * FROM comment",
                new BeanPropertyRowMapper<>(Comment.class));
    }

    public Comment getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM comment WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Comment.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Comment comment) {
        jdbcTemplate.update("INSERT INTO comment(id,localDateTime) VALUES(?,?)",
                comment.getId(), comment.getLocalDateTime());
    }

    public void update(UUID id, Comment updatedComment) {
        jdbcTemplate.update("UPDATE comment SET name=?, WHERE id=?",
                updatedComment.getLocalDateTime(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM comment WHERE id=?", id);
    }
}
