package spd.trello.service;

import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.WorkspaceDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class WorkspaceService extends ServiceLayer<Workspace> {

    private final WorkspaceDAO workspaceDAO;

    public WorkspaceService() {
        workspaceDAO=new WorkspaceDAO();
    }

    @Override
    public Workspace readById(UUID id) {
        return workspaceDAO.getById(id);
    }

    @Override
    public Workspace create() {
        Workspace workspace = new Workspace();
        workspace.setName("FirstWorkspace");
        workspace.setDescription("My lovely project about jdbc,flyway,database");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setUpdatedBy("myfeatureknowlange@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedDate(LocalDateTime.now());
        workspaceDAO.save(workspace);
        return workspace;
    }

    @Override
    public void update(UUID id,Workspace updated_workspace) {
        workspaceDAO.update(id, updated_workspace);
    }

    @Override
    public void delete(UUID id) {
        workspaceDAO.delete(id);
    }

    @Override
    public List<Workspace> getAll() {
        return workspaceDAO.getAll();
    }
}
