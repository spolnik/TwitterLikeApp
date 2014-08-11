package com.wordpress.nprogramming.commands;

import java.util.List;

public interface CommandParser {
    Command parse(String rawCommand, List<Command> commands);
}
