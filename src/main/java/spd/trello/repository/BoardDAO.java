package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Board;
import spd.trello.config.DataBaseConfiguration;

import java.util.List;
import java.util.UUID;

public class BoardDAO implements IRepository<Board> {
    private final JdbcTemplate jdbcTemplate;

    public BoardDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<Board> getAll() {
        return jdbcTemplate.query("SELECT * FROM board", new BeanPropertyRowMapper<>(Board.class));
    }

    public Board getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM board WHERE id=?", new BeanPropertyRowMapper<>(Board.class),id)
                .stream().findFirst().orElse(null);
    }

    public Board save(Board board) {
        jdbcTemplate.update("INSERT INTO board(id,name,description,workspace_id,visibility," +
                        "archived,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?,?,?)", board.getId(), board.getName(), board.getDescription(),
                board.getWorkspaceId(), board.getVisibility().toString(), board.getArchived(), board.getCreatedBy(),
                board.getUpdatedBy(), board.getCreatedDate(), board.getUpdatedDate());
        return board;
    }


    public Board update(UUID id, Board updatedBoard) {
        jdbcTemplate.update("UPDATE board SET name=?, description=?,workspace_id=?,visibility=?,archived=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updatedBoard.getName(),
                updatedBoard.getDescription(),updatedBoard.getWorkspaceId(),
                updatedBoard.getVisibility().toString(), updatedBoard.getArchived(), updatedBoard.getCreatedBy(), updatedBoard.getUpdatedBy(), updatedBoard.getCreatedDate(), updatedBoard.getUpdatedDate(), id);
        return updatedBoard;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM board WHERE id=?", id);
    }
}
