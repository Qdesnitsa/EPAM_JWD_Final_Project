package by.sidina.it_team.controller.servlet;

import by.sidina.it_team.controller.request_processor.HomeRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        HomeRequest requestProcessor = HomeRequest.getHomeRequest();
        try {
            requestProcessor.process(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HomeRequest requestProcessor = HomeRequest.getHomeRequest();
        try {
            requestProcessor.process(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
