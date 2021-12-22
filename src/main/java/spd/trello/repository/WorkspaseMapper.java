package spd.trello.repository;

import org.flywaydb.core.internal.jdbc.RowMapper;
import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class WorkspaseMapper implements RowMapper<Workspace> {

    @Override
    public Workspace mapRow(ResultSet rs) throws SQLException {
        Workspace workspace = new Workspace();
        workspace.setId((UUID) rs.getObject("id"));
        workspace.setName(rs.getString("name"));
        workspace.setDescription(rs.getString("description"));
        workspace.setVisibility((WorkspaceVisibility) rs.getObject("visibility"));
        workspace.setCreatedBy(rs.getString("createdBy"));
        workspace.setUpdatedBy(rs.getString("updatedBy"));
        workspace.setCreatedDate((LocalDateTime) rs.getObject("createdDate"));
        workspace.setUpdatedDate((LocalDateTime) rs.getObject("updatedDate"));
        return workspace;
    }
}
