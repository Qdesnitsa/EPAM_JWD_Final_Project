package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class HomeShowProjectsCommand implements BaseCommand {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return session.getAttribute(AttributeName.USER) != null;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.PAGE_NUMBER, request.getParameter(ParameterName.PAGE_NUMBER));
        return HomePageByRoleProvider.getProjectsPageForUser(user, request, response);
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return JSPPagePath.SIGN_IN;
    }
}
