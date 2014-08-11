package com.wordpress.nprogramming.commands;

public interface Command {
    void run(String rawCommand);
    boolean match(String rawCommand);
}
