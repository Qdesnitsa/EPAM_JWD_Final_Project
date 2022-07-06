package by.sidina.it_team.controller.servlet;

import by.sidina.it_team.controller.request_processor.SignInGetRequest;
import by.sidina.it_team.controller.request_processor.SignInPostRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignInServlet", value = "/sign-in")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInGetRequest requestProcessor = new SignInGetRequest();
        try {
            requestProcessor.process(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInPostRequest requestProcessor = new SignInPostRequest();
        try {
            requestProcessor.process(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
