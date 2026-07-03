package controller;

import java.io.IOException;

import dao.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Task;
import model.User;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddTaskServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        String taskName = request.getParameter("task");

        Task task = new Task();

        task.setUserId(user.getId());
        task.setTaskName(taskName);

        TaskDAO dao = new TaskDAO();

        boolean status = dao.addTask(task);

        if (status) {

            response.sendRedirect("tasks.jsp");

        } else {

            response.getWriter().println("<h2>Task Not Added</h2>");

        }

    }

}