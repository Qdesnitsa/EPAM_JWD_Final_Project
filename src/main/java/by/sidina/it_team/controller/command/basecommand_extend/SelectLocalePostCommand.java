package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.Command;
import by.sidina.it_team.controller.CommandProvider;
import by.sidina.it_team.controller.command.BaseCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SelectLocalePostCommand extends BaseCommand {

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    @Override
    public Command getExpectedCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("locale", request.getParameter("language"));
        String commandName = (String) session.getAttribute("last_command");
        Command command = CommandProvider.defineCommand(commandName);
        return command;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
