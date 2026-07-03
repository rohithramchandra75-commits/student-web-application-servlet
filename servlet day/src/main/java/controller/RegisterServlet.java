package controller;

import java.io.IOException;

import dao.RegistrationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Registration;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Read form values
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String course = request.getParameter("course");

        // Read selected skills
        String[] skillsArray = request.getParameterValues("skills");

        String skills = "";

        if (skillsArray != null) {
            skills = String.join(", ", skillsArray);
        }

        // Create Registration object
        Registration reg = new Registration();

        reg.setName(name);
        reg.setEmail(email);
        reg.setPhone(phone);
        reg.setGender(gender);
        reg.setCourse(course);
        reg.setSkills(skills);

        // Save into database
        RegistrationDAO dao = new RegistrationDAO();

        boolean status = dao.registerCourse(reg);

        if (status) {

            System.out.println("Course Registration Successful");

            response.sendRedirect("index.html");

        } else {

            System.out.println("Course Registration Failed");

            response.setContentType("text/html");

            response.getWriter().println("<h2>Registration Failed!</h2>");
            response.getWriter().println("<a href='register.html'>Try Again</a>");
        }
    }
}