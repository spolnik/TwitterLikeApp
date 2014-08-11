package com.wordpress.nprogramming.commands;

import java.util.List;

public class SimpleCommandParser implements CommandParser {

    @Override
    public Command parse(String rawCommand, List<Command> commands) {
        for (Command command : commands) {
            if (command.match(rawCommand))
                return command;
        }

        return new InvalidCommand();
    }
}
