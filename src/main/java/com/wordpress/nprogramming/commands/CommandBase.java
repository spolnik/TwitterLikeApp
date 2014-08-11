package com.wordpress.nprogramming.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandBase implements Command {
    @Override
    public abstract void run(String rawCommand);

    @Override
    public final boolean match(String rawCommand) {
        Matcher matcher = getCommandRegex().matcher(rawCommand);
        return matcher.find();
    }

    protected abstract Pattern getCommandRegex();
}
