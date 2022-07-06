package by.sidina.it_team.controller.servlet;

import by.sidina.it_team.controller.request_processor.NewProjectGetRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NewProjectServlet", value = "/new-project")
public class NewProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewProjectGetRequest requestProcessor = new NewProjectGetRequest();
        try {
            requestProcessor.process(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
