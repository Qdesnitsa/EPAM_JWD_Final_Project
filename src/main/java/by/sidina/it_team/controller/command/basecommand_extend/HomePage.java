package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomePage extends BaseCommand {
    private static HomePage instance = new HomePage();

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        if (user != null) {
            return HomePageByRoleProvider.getProjectsPageForUser(user, request);
        } else {
            return JSPPagePath.SIGN_IN;
        }
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    public static HomePage getHomeRequest() {
        return instance;
    }
}
