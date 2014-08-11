package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandBase implements Command {
    @Override
    public abstract String run(String rawCommand, TwitterAppContext context);

    @Override
    public final boolean match(String rawCommand) {
        Matcher matcher = getCommandRegex().matcher(rawCommand);
        return matcher.find();
    }

    protected abstract Pattern getCommandRegex();
}
