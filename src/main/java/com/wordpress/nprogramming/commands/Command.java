package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.AppContext;

public interface Command {
    String run(String rawCommand, AppContext context);
    boolean match(String rawCommand);
}
