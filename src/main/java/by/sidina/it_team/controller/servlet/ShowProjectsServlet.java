package by.sidina.it_team.controller.servlet;

import by.sidina.it_team.controller.request_processor.NewProjectPostRequest;
import by.sidina.it_team.controller.request_processor.HomePageGetRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowProjectsServlet", value = "/projects")
public class ShowProjectsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomePageGetRequest requestProcessor = new HomePageGetRequest();
        try {
            requestProcessor.process(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewProjectPostRequest requestProcessor = new NewProjectPostRequest();
        try {
            requestProcessor.process(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
