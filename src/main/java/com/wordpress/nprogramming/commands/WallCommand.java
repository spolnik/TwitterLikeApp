package com.wordpress.nprogramming.commands;

import java.util.regex.Pattern;

public class WallCommand extends CommandBase {

    private static final Pattern regex = Pattern.compile("^(\\S+) wall$");

    @Override
    public void run(String rawCommand) {

    }

    @Override
    protected Pattern getCommandRegex() {
        return regex;
    }
}
