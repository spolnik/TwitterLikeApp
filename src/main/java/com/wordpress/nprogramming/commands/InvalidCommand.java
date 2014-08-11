package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;

public class InvalidCommand implements Command {

    @Override
    public String run(String rawCommand, TwitterAppContext context) {
        return String.format("Command: %s is invalid!", rawCommand);
    }

    @Override
    public boolean match(String rawCommand) {
        return false;
    }
}
