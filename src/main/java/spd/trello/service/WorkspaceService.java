package spd.trello.service;

import spd.trello.domain.Workspace;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class WorkspaceService extends ServiceLayer<Workspace> {

    public WorkspaceService(IRepository<Workspace> repository) {
        super(repository);
    }

    @Override
    public Workspace readById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public Workspace create(Workspace workspace) {
        return repository.save(workspace);
    }

    @Override
    public void update(UUID id,Workspace workspace) {
        repository.update(id, workspace);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public List<Workspace> getAll() {
        return repository.getAll();
    }
}
