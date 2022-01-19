package spd.trello.service;

import spd.trello.domain.Workspace;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public class WorkspaceService extends ServiceLayer<Workspace> {

    public WorkspaceService(IRepository<Workspace> repository) {
        super(repository);
    }
}
