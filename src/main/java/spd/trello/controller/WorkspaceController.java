package spd.trello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spd.trello.domain.Workspace;
import spd.trello.service.WorkspaceService;

import java.util.UUID;

@Controller
@RequestMapping("/workspace")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping()
    public String all(Model model) {
        model.addAttribute("workspaces", workspaceService.getAll());
        return "workspace/workspace-list";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("workspace", workspaceService.readById(id));
        return "workspace/read-workspace";
    }
    @GetMapping("/new")
    public String newWorkspace(@ModelAttribute("workspace") Workspace workspace) {
        return "workspace/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("workspace") Workspace workspace) {
        workspaceService.create(workspace);
        return "redirect:/workspace";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("workspace", workspaceService.readById(id));
        return "workspace/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("workspace") Workspace workspace, @PathVariable("id") UUID id) {
        workspaceService.update(id, workspace);
        return "redirect:/workspace";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        workspaceService.delete(id);
        return "redirect:/workspace";
    }
}
