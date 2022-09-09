package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomePage implements BaseCommand {

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        if (user != null) {
            return HomePageByRoleProvider.getProjectsPageForUser(user, request, response);
        } else {
            return JSPPagePath.SIGN_IN;
        }
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return JSPPagePath.SIGN_IN;
    }
}
