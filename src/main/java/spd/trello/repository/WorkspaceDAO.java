package spd.trello.repository;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import spd.trello.domain.Workspace;
import test.app.DataBaseConfiguration;

import java.sql.SQLException;
import java.util.UUID;

public class WorkspaceDAO {

    private JdbcTemplate jdbcTemplate;

    public WorkspaceDAO() {
        try {
            this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Workspace read(UUID id)  {
        Workspace workspace=null;
        try {
            workspace= jdbcTemplate.query("SELECT * FROM workspace WHERE id=?", new WorkspaseMapper(), id)
                    .stream().findFirst().orElse(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workspace;
    }

    public void save(Workspace workspace) {
        try {
            jdbcTemplate.update("INSERT INTO workspace VALUES(?,?,?,?,?,?,?,?)",workspace.getId(),
                    workspace.getName(), workspace.getDescription(), workspace.getVisibility().toString(), workspace.getCreatedBy(),
                    workspace.getUpdatedBy(), workspace.getCreatedDate(), workspace.getUpdatedDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(UUID id, Workspace updatedWorkspace) {
        try {
            jdbcTemplate.update("UPDATE workspace SET name=?, description=?, visibility=?, " +
                            "createdBy=?,updatedBy=?,createdDate=?,updatedDate=? WHERE id=?", updatedWorkspace.getName(),
                    updatedWorkspace.getDescription(), updatedWorkspace.getVisibility(), updatedWorkspace.getCreatedBy(),
                    updatedWorkspace.getUpdatedBy(), updatedWorkspace.getCreatedDate(), updatedWorkspace.getUpdatedDate(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(UUID id) {
        try {
            jdbcTemplate.update("DELETE FROM workspace WHERE id=?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


