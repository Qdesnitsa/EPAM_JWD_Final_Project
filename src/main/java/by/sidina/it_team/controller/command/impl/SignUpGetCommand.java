package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpGetCommand implements BaseCommand {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeName.USER) != null) {
            return false;
        }
        return true;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        return JSPPagePath.SIGN_UP;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return HomePageByRoleProvider.getProjectsPageForUser(user, request, response);
    }
}
