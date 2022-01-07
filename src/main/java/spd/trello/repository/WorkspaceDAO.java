package spd.trello.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Workspace;
import spd.trello.config.DataBaseConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class WorkspaceDAO implements IRepository<Workspace>{

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public WorkspaceDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
        this.dataSource = dataSource;
    }

    public List<Workspace> getAll() {
        return jdbcTemplate.query("SELECT * FROM workspace", new BeanPropertyRowMapper<>(Workspace.class));
    }

    public Workspace getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM workspace WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Workspace.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Workspace workspace) {

        jdbcTemplate.update("INSERT INTO workspace(id,name,description,visibility,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?)", workspace.getId(), workspace.getName(), workspace.getDescription(), workspace.getVisibility().toString(),
                workspace.getCreatedBy(), workspace.getUpdatedBy(), workspace.getCreatedDate(), workspace.getUpdatedDate());
    }

    public void update(UUID id, Workspace updatedWorkspace) {
        jdbcTemplate.update("UPDATE workspace SET name=?, description=?, visibility=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updatedWorkspace.getName(),
                updatedWorkspace.getDescription(), updatedWorkspace.getVisibility().toString(), updatedWorkspace.getCreatedBy(),
                updatedWorkspace.getUpdatedBy(), updatedWorkspace.getCreatedDate(), updatedWorkspace.getUpdatedDate(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM workspace WHERE id=?", id);
    }
}


