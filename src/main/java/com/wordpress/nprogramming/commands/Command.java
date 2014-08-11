package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;

public interface Command {
    void run(String rawCommand, TwitterAppContext context);
    boolean match(String rawCommand);
}
