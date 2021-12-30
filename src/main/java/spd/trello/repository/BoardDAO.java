package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Board;
import test.app.DataBaseConfiguration;

import java.util.List;
import java.util.UUID;

public class BoardDAO {
    private JdbcTemplate jdbcTemplate;

    public BoardDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<Board> getAll() {
        return jdbcTemplate.query("SELECT * FROM board", new BeanPropertyRowMapper<>(Board.class));
    }

    public Board getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM board WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Board.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Board board) {
        jdbcTemplate.update("INSERT INTO board(id,name,description,visibility,archived,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?,?)", board.getId(), board.getName(), board.getDescription(),
                board.getVisibility().toString(), board.getArchived(), board.getCreatedBy(), board.getUpdatedBy(), board.getCreatedDate(), board.getUpdatedDate());
    }


    public void update(UUID id, Board updatedBoard) {
        jdbcTemplate.update("UPDATE board SET name=?, description=?, visibility=?,archived=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updatedBoard.getName(), updatedBoard.getDescription(),
                updatedBoard.getVisibility().toString(), updatedBoard.getArchived(), updatedBoard.getCreatedBy(), updatedBoard.getUpdatedBy(), updatedBoard.getCreatedDate(), updatedBoard.getUpdatedDate(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM board WHERE id=?", id);
    }
}
