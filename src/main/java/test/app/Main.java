package test.app;

import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.WorkspaceDAO;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DataBaseConfiguration.startMigration();
        Workspace workspace = new Workspace();
        workspace.setName("FirstWorkspace");
        workspace.setDescription("My lovely project about jdbc,flyway,database");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedDate(LocalDateTime.now());
        WorkspaceDAO workspaceDAO = new WorkspaceDAO();
        workspaceDAO.save(workspace);
        System.out.println(workspaceDAO.getById(workspace.getId()));

        Workspace updated_workspace = new Workspace();
        updated_workspace.setName("NewWorkspace");
        updated_workspace.setDescription("new challenges");
        updated_workspace.setVisibility(WorkspaceVisibility.PRIVATE);
        updated_workspace.setCreatedBy(workspace.getCreatedBy());
        updated_workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        updated_workspace.setCreatedDate(workspace.getCreatedDate());
        updated_workspace.setUpdatedDate(LocalDateTime.now());
        workspaceDAO.update(workspace.getId(), updated_workspace);
        System.out.println(workspaceDAO.getById(workspace.getId()));

        for (Workspace workspace1 : workspaceDAO.getAll()) {
            System.out.println(workspace1);
        }

        //workspaceDAO.delete(workspace.getId());
    }
}
