package by.sidina.it_team.controller.servlet;

import by.sidina.it_team.controller.request_processor.EditProjectPostRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditProjectServlet", value = "/edit-project")
public class EditProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EditProjectPostRequest requestProcessor = new EditProjectPostRequest();
        try {
            requestProcessor.process(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
