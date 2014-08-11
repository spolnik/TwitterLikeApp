package com.wordpress.nprogramming.commands;

public class InvalidCommand implements Command {

    @Override
    public void run(String rawCommand) {

    }

    @Override
    public boolean match(String rawCommand) {
        return true;
    }
}
