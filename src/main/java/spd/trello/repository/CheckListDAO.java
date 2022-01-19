package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.CheckList;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.Workspace;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class CheckListDAO implements IRepository<CheckList>{
    private final JdbcTemplate jdbcTemplate;

    public CheckListDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<CheckList> getAll() {
        return jdbcTemplate.query("SELECT * FROM checklist",
                new BeanPropertyRowMapper<>(CheckList.class));
    }

    public CheckList getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM checklist WHERE id=?",
                        new BeanPropertyRowMapper<>(CheckList.class),id)
                .stream().findFirst().orElse(null);
    }

    public CheckList save(CheckList checkList) {
        jdbcTemplate.update("INSERT INTO checklist(id,name,card_id,created_by,updated_by,created_date,updated_date) VALUES(?,?)",
                checkList.getId(), checkList.getName(),checkList.getCardId(),checkList.getCreatedBy(),
                checkList.getCreatedDate(), checkList.getUpdatedBy(),checkList.getUpdatedDate());
        return checkList;
    }

    public CheckList update(UUID id, CheckList updatedCheckList) {
        jdbcTemplate.update("UPDATE checklist SET name=? card_id=?" +
                        "created_by=?,updated_by=?,created_date=?,updated_date=?, WHERE id=?",
                updatedCheckList.getName(), updatedCheckList.getCardId(),updatedCheckList.getCreatedBy(),
                updatedCheckList.getCreatedDate(), updatedCheckList.getUpdatedBy(),updatedCheckList.getUpdatedDate(),  id);
        return updatedCheckList;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM checklist WHERE id=?", id);
    }
}
