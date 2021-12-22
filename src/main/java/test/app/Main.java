package test.app;

import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.WorkspaceDAO;
import spd.trello.repository.WorkspaceRepo;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args){
        DataBaseConfiguration.startMigration();
        Workspace workspace = new Workspace();
        workspace.setName("FirstWorkspace");
        workspace.setDescription("My lovely project about jdbc,flyway,database");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedDate(LocalDateTime.now());
        new WorkspaceDAO().save(workspace);
    }
}
