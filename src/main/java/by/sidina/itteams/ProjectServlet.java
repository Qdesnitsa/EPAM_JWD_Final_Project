package by.sidina.itteams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "project", value = "/project")
public class ProjectServlet extends HttpServlet {
    private String message;

    public void init() {message = "Page";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("age", 34);
        request.setAttribute("projects", new String[] { "Project 1", "Project 2" });

        getServletContext().getRequestDispatcher("/projects.jsp").forward(request, response);
    }

    public void destroy() {
    }
}