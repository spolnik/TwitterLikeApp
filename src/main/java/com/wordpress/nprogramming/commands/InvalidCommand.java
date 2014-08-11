package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;

public class InvalidCommand implements Command {

    @Override
    public void run(String rawCommand, TwitterAppContext context) {

    }

    @Override
    public boolean match(String rawCommand) {
        return true;
    }
}
