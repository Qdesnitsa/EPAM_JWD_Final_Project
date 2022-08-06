package by.sidina.it_team.controller;

import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
//    private static final Logger LOGGER = LogManager.getLogger();
//
//    @Override
//    public void init() {
//        LOGGER.info("Controller servlet has been initialized");
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String commandFromPage = request.getParameter(ParameterName.COMMAND);
        Command command = CommandProvider.defineCommand(commandFromPage);
        command.execute(request, response);
    }
}