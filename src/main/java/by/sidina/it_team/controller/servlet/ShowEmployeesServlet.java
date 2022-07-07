package by.sidina.it_team.controller.servlet;

import by.sidina.it_team.controller.request_processor.ShowEmployeesGetRequest;
import by.sidina.it_team.controller.request_processor.ShowProjectsGetRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowEmployeesServlet", value = "/show-employees")
public class ShowEmployeesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShowEmployeesGetRequest requestProcessor = new ShowEmployeesGetRequest();
        try {
            requestProcessor.process(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
