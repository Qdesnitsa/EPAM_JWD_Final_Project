package by.sidina.page_servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public abstract class BasePageServlet extends HttpServlet {
    private String page;

    public BasePageServlet(String page) {
        this.page = page;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            prepareDataForPage(request);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher(this.page).forward(request, response);
    }

    public void prepareDataForPage(HttpServletRequest request) throws ParseException {

    }
}
