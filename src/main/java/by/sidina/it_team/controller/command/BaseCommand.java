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
    public abstract boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response);

    public abstract String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException, ServiceException;

    public abstract String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response);

    public default Command getExpectedCommand(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public default String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        if (canBeExpectedResponseReturned(request, response)) {
            HttpSession session = request.getSession();
            String commandName = request.getParameter(ParameterName.COMMAND);
            if (!commandName.toUpperCase().equals(CommandEnum.SELECT_LOCALE.name())) {
                session.setAttribute(AttributeName.LAST_COMMAND, request.getParameter(ParameterName.COMMAND));
            }
            String jspPage = getExpectedJspPage(request, response);
            if (jspPage != null) {
                try {
                    request.getRequestDispatcher(jspPage).forward(request, response);
                } catch (Exception e) {
                    /**
                     logger.log(e);
                     return "error.jsp";
                     */
                    throw new RuntimeException(e);
                }
            } else {
                Command command = getExpectedCommand(request, response);
                if (command != null) {
                    command.execute(request, response);
                }
            }
        } else {
            try {
                request.getRequestDispatcher(getAlternativeJspPage(request, response)).forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
