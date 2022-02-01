package spd.trello.controller;

import org.springframework.web.bind.annotation.*;
import spd.trello.domain.Workspace;
import spd.trello.service.WorkspaceService;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController extends AbstractController<Workspace,WorkspaceService>{


    public WorkspaceController(WorkspaceService service) {
        super(service);
    }
}
