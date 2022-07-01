package by.sidina.page_servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-home")
public class AdminHomeServlet extends BasePageServlet {
    public AdminHomeServlet() {
        super("/page/home/admin_home.jsp");
    }


}
