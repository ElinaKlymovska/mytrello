package spd.trello.servlet;

import spd.trello.repository.WorkspaceDAO;
import spd.trello.service.WorkspaceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/workspace/list")
public class WorkspaceLIstServlet extends HttpServlet {

    private WorkspaceService workspaceService;

    @Override
    public void init() throws ServletException {
        workspaceService=new WorkspaceService(WorkspaceDAO.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/webapp/workspace-list.jsp");
        request.setAttribute("workspaces",workspaceService.getAll());
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
