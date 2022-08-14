package by.sidina.it_team.controller.command;

import by.sidina.it_team.controller.Command;
import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface BaseCommand extends Command {
    boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response);

    String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;

    String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response);

    default Command getExpectedCommand(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    default String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        if (canBeExpectedResponseReturned(request, response)) {
            HttpSession session = request.getSession();
            String commandName = request.getParameter(ParameterName.COMMAND);
            if (!commandName.toUpperCase().equals(CommandEnum.SELECT_LOCALE.name())) {
                session.setAttribute(AttributeName.LAST_COMMAND, request.getParameter(ParameterName.COMMAND));
            }
            String jspPage = getExpectedJspPage(request, response);
            if (jspPage != null) {
                request.getRequestDispatcher(jspPage).forward(request, response);
            } else {
                Command command = getExpectedCommand(request, response);
                if (command != null) {
                    command.execute(request, response);
                }
            }
        } else {
            request.getRequestDispatcher(getAlternativeJspPage(request, response)).forward(request, response);
        }
        return null;
    }
}
