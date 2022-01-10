package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Comment;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.Workspace;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class CommentDAO implements IRepository<Comment>{
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public CommentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
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
        jdbcTemplate.update("INSERT INTO comment(id,member_id,localdatetime,text,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?)", comment.getId(),comment.getMember().getId(), comment.getLocalDateTime(), comment.getText(),comment.getCreatedBy(),
                comment.getCreatedDate(),comment.getUpdatedBy(),comment.getUpdatedDate());
    }

    public void update(UUID id, Comment updatedComment) {
        jdbcTemplate.update("UPDATE comment SET member_id=?,localdatetime=?,text=?,created_by=?,updated_by=?,created_date=?,updated_date=?, WHERE id=?",
                updatedComment.getLocalDateTime(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM comment WHERE id=?", id);
    }
}
