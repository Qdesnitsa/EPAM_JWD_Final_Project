package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class HomeShowProjectsCommand extends BaseCommand {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return session.getAttribute(AttributeName.USER) != null;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.PAGE_NUMBER, request.getParameter(ParameterName.PAGE_NUMBER));
        request.setAttribute(AttributeName.PROJECT_STATUS, request.getParameter(ParameterName.PROJECT_STATUS));
        return HomePageByRoleProvider.getProjectsPageForUser(user, request);
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return JSPPagePath.SIGN_IN;
    }
}
