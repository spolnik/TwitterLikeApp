package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;

import java.util.regex.Pattern;

public class FollowingCommand extends CommandBase {

    private static final Pattern regex = Pattern.compile("^(\\S+) follows (\\S+)$");

    @Override
    public void run(String rawCommand, TwitterAppContext context) {

    }

    @Override
    protected Pattern getCommandRegex() {
        return regex;
    }
}