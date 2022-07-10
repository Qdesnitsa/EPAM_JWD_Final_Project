package by.sidina.it_team.controller.servlet;

import by.sidina.it_team.controller.request_processor.EditCustomerPostRequest;
import by.sidina.it_team.controller.request_processor.EditEmployeePostRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditCustomerServlet", value = "/edit-customer")
public class EditCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EditCustomerPostRequest requestProcessor = new EditCustomerPostRequest();
        try {
            requestProcessor.process(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
