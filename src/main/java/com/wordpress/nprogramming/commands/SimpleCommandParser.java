package com.wordpress.nprogramming.commands;

public class SimpleCommandParser implements CommandParser {
    @Override
    public Command parse(String rawCommand) {
        return new InvalidCommand();
    }
}
