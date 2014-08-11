package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;

import java.util.regex.Pattern;

public class WallCommand extends CommandBase {

    private static final Pattern regex = Pattern.compile("^(\\S+) wall$");

    @Override
    public String run(String rawCommand, TwitterAppContext context) {
        return null;
    }

    @Override
    protected Pattern getCommandRegex() {
        return regex;
    }
}
