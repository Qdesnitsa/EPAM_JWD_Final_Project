package by.sidina.page_servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/developer-home")
public class DeveloperHomeServlet extends BasePageServlet {
    public DeveloperHomeServlet() {
        super("/page/home/employee_projects.jsp");
    }
}
