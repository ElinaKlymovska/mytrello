package spd.trello.servlet;

import spd.trello.domain.Member;
import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.WorkspaceDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/")
public class CreateWorkspaceServlet extends HttpServlet {

    private WorkspaceDAO workspaceDAO;

    @Override
    public void init() throws ServletException {
        workspaceDAO=WorkspaceDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/webapp/create-workspace.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        WorkspaceVisibility visibility = WorkspaceVisibility.valueOf(request.getParameter("visibility"));
        Workspace workspace= new Workspace();
        workspace.setName(name);
        workspace.setDescription(description);
        workspace.setVisibility(visibility);
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedBy("someFeture@gmail.com");
        workspace.setUpdatedDate(LocalDateTime.now());
        workspaceDAO.save(workspace);
    }
}
