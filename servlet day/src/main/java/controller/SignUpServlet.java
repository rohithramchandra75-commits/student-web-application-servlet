package controller;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read form values
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Print values in Eclipse Console
        System.out.println("========== SIGNUP ==========");
        System.out.println("Name : " + name);
        System.out.println("Email : " + email);
        System.out.println("Password : " + password);

        // Create User Object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        // Call DAO
        UserDAO dao = new UserDAO();

        boolean status = dao.registerUser(user);

        if (status) {

            System.out.println("User Registered Successfully");

            // Redirect to Login Page
            response.sendRedirect("login.html");

        } else {

            System.out.println("Registration Failed");

            response.setContentType("text/html");

            response.getWriter().println("<h2>Registration Failed!</h2>");
            response.getWriter().println("<a href='signup.html'>Try Again</a>");
        }
    }
}