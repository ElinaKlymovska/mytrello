package spd.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spd.trello.domain.Workspace;
import spd.trello.repository.IRepository;
import spd.trello.repository.WorkspaceDAO;

import java.util.List;
import java.util.UUID;
@Service
public class WorkspaceService extends ServiceLayer<Workspace, WorkspaceDAO> {

    public WorkspaceService(WorkspaceDAO repository) {
        super(repository);
    }

}
