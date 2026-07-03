package controller;

import java.io.IOException;

import dao.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteTaskServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        TaskDAO dao = new TaskDAO();

        boolean status = dao.deleteTask(id);

        if (status) {

            response.sendRedirect("tasks.jsp");

        } else {

            response.getWriter().println("<h2>Task Not Deleted</h2>");

        }

    }

}