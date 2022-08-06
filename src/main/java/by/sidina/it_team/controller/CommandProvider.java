package by.sidina.it_team.controller;

import by.sidina.it_team.controller.command.CommandEnum;

public class CommandProvider {
    //private static final Logger LOGGER = LogManager.getLogger();

    public static Command defineCommand(String command) {
        Command current;
        //LOGGER.debug(command);
        if (command == null || command.isEmpty()) {
            //LOGGER.debug("Empty command");
            return new UnknownCommand();
        }
        try {
            CommandEnum currentType = CommandEnum.valueOf(command.toUpperCase());
            current = currentType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            //LOGGER.debug("Empty command from catch block");
            current = new UnknownCommand();
        }
        return current;
    }
}
