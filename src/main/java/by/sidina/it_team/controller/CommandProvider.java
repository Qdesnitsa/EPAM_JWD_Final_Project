package by.sidina.it_team.controller;

import by.sidina.it_team.controller.impl.CommandName;
import by.sidina.it_team.controller.impl.EditProjectCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();

    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGN_UP, new EditProjectCommand());
        repository.put(CommandName.SIGN_IN, new EditProjectCommand());
        repository.put(CommandName.SIGN_OUT, new EditProjectCommand());
        repository.put(CommandName.ADD_PROJECT, new EditProjectCommand());
        repository.put(CommandName.ADD_EMPLOYEE, new EditProjectCommand());
        repository.put(CommandName.ADD_CUSTOMER, new EditProjectCommand());
        repository.put(CommandName.EDIT_PROJECT, new EditProjectCommand());
        repository.put(CommandName.EDIT_EMPLOYEE, new EditProjectCommand());
        repository.put(CommandName.EDIT_CUSTOMER, new EditProjectCommand());
        repository.put(CommandName.POST_HOURS, new EditProjectCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String name){
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch(IllegalArgumentException | NullPointerException e){
            //write log
            command = repository.get(CommandName.UNKNOWN_COMMAND);
        }

        return command;
    }
}
