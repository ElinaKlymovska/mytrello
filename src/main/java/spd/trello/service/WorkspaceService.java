package spd.trello.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Workspace;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;
@Service
public class WorkspaceService extends ServiceLayer<Workspace> {

    public WorkspaceService(IRepository<Workspace> repository) {
        super(repository);
    }
}
