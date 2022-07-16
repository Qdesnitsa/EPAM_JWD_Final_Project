package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.HomePageByRoleProvider;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInGetCommand extends BaseCommand {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute(ParameterName.USER) != null) {
            return false;
        }
        return true;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        return JSPPagePath.SIGN_IN;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        return HomePageByRoleProvider.getProjectsPageForUser(user, request);
    }
}
