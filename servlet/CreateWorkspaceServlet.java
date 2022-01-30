package spd.trello.servlet;

import spd.trello.domain.Workspace;
import spd.trello.domain.WorkspaceVisibility;
import spd.trello.repository.WorkspaceDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/workspace/create")
public class CreateWorkspaceServlet extends HttpServlet {

    private WorkspaceDAO workspaceDAO;

    @Override
    public void init() throws ServletException {
        workspaceDAO=WorkspaceDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/create-workspace.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Workspace workspace= new Workspace();
        workspace.setName(request.getParameter("name"));
        workspace.setDescription(request.getParameter("description"));
        workspace.setVisibility( WorkspaceVisibility.valueOf(request.getParameter("visibility")));
        workspace.setCreatedBy("klymovska.elina@gmail.com");
        workspace.setCreatedDate(LocalDateTime.now());
        workspace.setUpdatedBy("someFeture@gmail.com");
        workspace.setUpdatedDate(LocalDateTime.now());
        workspaceDAO.save(workspace);
        response.sendRedirect("/");
    }
}
