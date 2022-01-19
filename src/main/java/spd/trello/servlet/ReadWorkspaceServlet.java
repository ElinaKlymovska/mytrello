package spd.trello.servlet;

import spd.trello.domain.Workspace;
import spd.trello.repository.WorkspaceDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/workspace/read")
public class ReadWorkspaceServlet extends HttpServlet {
    private WorkspaceDAO workspaceDAO;

    @Override
    public void init() throws ServletException {
        workspaceDAO=WorkspaceDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Workspace workspace=workspaceDAO.getById(UUID.fromString(request.getParameter("id")));
        request.setAttribute("workspace", workspace);
        request.getRequestDispatcher("/read-workspace.jsp").forward(request,response);
    }

}
