package spd.trello.repository;

import spd.trello.domain.Workspace;
import test.app.DataBaseConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkspaceRepo {
    private static Connection connection;

    static {
        try {
            connection = DataBaseConfiguration.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save (Workspace workspace) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO workspace VALUES(" + workspace.getId() + ",'" + workspace.getName() +
                    "','" + workspace.getDescription() + "','" + workspace.getVisibility() + "','" + workspace.getCreatedBy() +
                    "','" + workspace.getUpdatedBy() + "'," + workspace.getCreatedDate() + workspace.getUpdatedDate()+")";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
