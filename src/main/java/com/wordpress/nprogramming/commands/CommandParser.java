package com.wordpress.nprogramming.commands;

public interface CommandParser {
    Command parse(String rawCommand);
}
