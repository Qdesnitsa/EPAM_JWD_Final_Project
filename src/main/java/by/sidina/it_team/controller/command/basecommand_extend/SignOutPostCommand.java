package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutPostCommand extends BaseCommand {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.USER, null);
        session.invalidate();
        //request.setAttribute("called", "called");
        return JSPPagePath.INDEX;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
