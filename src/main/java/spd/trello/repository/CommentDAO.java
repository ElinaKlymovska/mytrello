package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spd.trello.domain.Comment;

import java.util.List;
import java.util.UUID;
@Component
public class CommentDAO implements IRepository<Comment>{
    private final JdbcTemplate jdbcTemplate;

    public CommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Comment> getAll() {
        return jdbcTemplate.query("SELECT * FROM comment",
                new BeanPropertyRowMapper<>(Comment.class));
    }

    public Comment getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM comment WHERE id=?",
                        new BeanPropertyRowMapper<>(Comment.class),id)
                .stream().findFirst().orElse(null);
    }

    public Comment save(Comment comment) {
        jdbcTemplate.update("INSERT INTO comment(id,member_id,localdatetime,card_id,text," +
                        "created_by,created_date,updated_by,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?,?)", comment.getId(),comment.getMember().getId(),
                comment.getLocalDateTime(),comment.getCardId(), comment.getText(),comment.getCreatedBy(),
                comment.getCreatedDate(),comment.getUpdatedBy(),comment.getUpdatedDate());
        return comment;
    }

    public Comment update(UUID id, Comment comment) {
        jdbcTemplate.update("UPDATE comment SET member_id=?,localdatetime=?,card_id=?,text=?," +
                        "created_by=?,updated_by=?,created_date=?,updated_date=?, WHERE id=?",
                comment.getMember().getId(), comment.getLocalDateTime(),comment.getCardId(), comment.getText(),comment.getCreatedBy(),
                comment.getCreatedDate(),comment.getUpdatedBy(),comment.getUpdatedDate(), id);
        return comment;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM comment WHERE id=?", id);
    }
}
