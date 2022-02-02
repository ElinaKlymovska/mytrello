//package spd.trello.servlet;
//
//import spd.trello.repository.WorkspaceDAO;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/")
//public class WorkspaceLIstServlet extends HttpServlet {
//
//    private WorkspaceDAO workspaceDAO;
//
//    @Override
//    public void init() throws ServletException {
//        workspaceDAO = WorkspaceDAO.getInstance();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/workspace-list.jsp");
//        request.setAttribute("workspaces", workspaceDAO.getAll());
//        requestDispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
