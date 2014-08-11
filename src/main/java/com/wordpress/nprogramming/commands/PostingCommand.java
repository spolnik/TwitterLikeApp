package com.wordpress.nprogramming.commands;

import java.util.regex.Pattern;

public class PostingCommand extends CommandBase {

    private static final Pattern regex = Pattern.compile("^(\\S+) -> (.+)$");

    @Override
    public void run(String rawCommand) {

    }

    @Override
    protected Pattern getCommandRegex() {
        return regex;
    }
}
