package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.Command;
import by.sidina.it_team.controller.CommandProvider;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SelectLocalePostCommand implements BaseCommand {

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

    public static Command getExpectedCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.LOCALE, request.getParameter(ParameterName.LANGUAGE));
        String commandName = (String) session.getAttribute(AttributeName.LAST_COMMAND);
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