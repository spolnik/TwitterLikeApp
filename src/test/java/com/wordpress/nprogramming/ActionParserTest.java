package com.wordpress.nprogramming;

import com.wordpress.nprogramming.commands.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ActionParserTest {

    private SimpleCommandParser commandParser;
    private List<Command> commands;

    @Before
    public void setUp() throws Exception {
        commandParser = new SimpleCommandParser();

        commands = new ArrayList<>();
        commands.add(new PostingCommand());
        commands.add(new ReadingCommand());
        commands.add(new FollowingCommand());
        commands.add(new WallCommand());
    }

    @Test
    public void test_that_user_typing_posting_command_creates_posting_command() throws Exception {
        checkCommand(CommandSamples.POSTING_COMMAND, PostingCommand.class.getSimpleName());
    }

    @Test
    public void test_that_user_typing_reading_command_creates_reading_command() throws Exception {
        checkCommand(CommandSamples.READING_COMMAND, ReadingCommand.class.getSimpleName());
    }

    @Test
    public void test_that_user_typing_following_command_creates_following_command() throws Exception {
        checkCommand(CommandSamples.FOLLOWING_COMMAND, FollowingCommand.class.getSimpleName());
    }

    @Test
    public void test_that_user_typing_wall_command_creates_wall_command() throws Exception {
        checkCommand(CommandSamples.WALL_COMMAND, WallCommand.class.getSimpleName());
    }

    @Test
    public void test_that_any_wrong_user_input_results_in_creating_invalid_command() throws Exception {
        checkCommand(CommandSamples.INVALID_COMMAND, InvalidCommand.class.getSimpleName());
    }

    private void checkCommand(String rawCommand, String expectedClassName) {
        Command postingCommand = commandParser.parse(rawCommand, commands);
        assertThat(postingCommand.getClass().getSimpleName(), is(expectedClassName));
    }
}
