package by.sidina.page_servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddProjectServlet", value = "/add_project")
public class AddProjectServlet extends BasePageServlet {
    public AddProjectServlet() {
        super("/page/home/add_project.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("shortname", request.getParameter("shortname"));
        request.setAttribute("description", request.getParameter("description"));
        getServletContext().getRequestDispatcher("/pages/home/project.jsp").forward(request, response);
    }

    public void prepareDataForPage(HttpServletRequest request) {

    }
}
