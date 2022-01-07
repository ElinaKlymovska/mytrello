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
    private final DataSource dataSource;

    public CheckListDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<CheckList> getAll() {
        return jdbcTemplate.query("SELECT * FROM checklist",
                new BeanPropertyRowMapper<>(CheckList.class));
    }

    public CheckList getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM checklist WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(CheckList.class))
                .stream().findFirst().orElse(null);
    }

    public void save(CheckList checkList) {
        jdbcTemplate.update("INSERT INTO checklist(id,name) VALUES(?,?)",
                checkList.getId(), checkList.getName());
    }

    public void update(UUID id, CheckList updatedCheckList) {
        jdbcTemplate.update("UPDATE checklist SET name=?, WHERE id=?",
                updatedCheckList.getName(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM checklist WHERE id=?", id);
    }
}
