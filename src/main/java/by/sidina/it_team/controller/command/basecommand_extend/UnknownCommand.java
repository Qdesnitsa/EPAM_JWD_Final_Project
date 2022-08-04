package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

public class UnknownCommand extends BaseCommand {

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response)
            throws DAOException, ServletException, IOException {
        return null;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        HttpSession session = request.getSession();
        if (session.getAttribute(ParameterName.USER) != null) {
            User user = (User) session.getAttribute(ParameterName.USER);
            return HomePageByRoleProvider.getProjectsPageForUser(user, request);
        } else {
            return JSPPagePath.SIGN_IN;
        }
    }
}
