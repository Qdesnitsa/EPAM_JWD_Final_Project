package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.Command;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class UnknownCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOGGER.info("Unknown command");
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        HttpSession session = request.getSession();
        if (session.getAttribute(ParameterName.USER) != null) {
            User user = (User) session.getAttribute(ParameterName.USER);
            return HomePageByRoleProvider.getProjectsPageForUser(user, request, response);
        } else {
            return JSPPagePath.SIGN_IN;
        }
    }
}
