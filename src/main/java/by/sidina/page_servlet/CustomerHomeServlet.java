package by.sidina.page_servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/customer-home")
public class CustomerHomeServlet extends BasePageServlet {
    public CustomerHomeServlet() {
        super("/page/home/customer_home.jsp");
    }
}
