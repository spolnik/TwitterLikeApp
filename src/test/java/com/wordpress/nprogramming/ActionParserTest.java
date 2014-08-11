package com.wordpress.nprogramming;

import com.wordpress.nprogramming.commands.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ActionParserTest {

    private SimpleCommandParser commandParser;

    @Before
    public void setUp() throws Exception {
        commandParser = new SimpleCommandParser();
    }

    @Test
    public void test_that_user_typing_posting_command_creates_posting_command() throws Exception {
        checkCommand("Mikolaj -> Hello dad!", PostingCommand.class.getSimpleName());
    }

    @Test
    public void test_that_user_typing_reading_command_creates_reading_command() throws Exception {
        checkCommand("Mikolaj", ReadingCommand.class.getSimpleName());
    }

    @Test
    public void test_that_user_typing_following_command_creates_following_command() throws Exception {
        checkCommand("Mikolaj follows Julia", FollowingCommand.class.getSimpleName());
    }

    @Test
    public void test_that_user_typing_wall_command_creates_wall_command() throws Exception {
        checkCommand("Mikolaj wall", WallCommand.class.getSimpleName());
    }

    @Test
    public void test_that_any_wrong_user_input_results_in_creating_invalid_command() throws Exception {
        checkCommand("  ", InvalidCommand.class.getSimpleName());
    }

    private void checkCommand(String rawCommand, String expectedClassName) {
        Command postingCommand = commandParser.parse(rawCommand);
        assertThat(postingCommand.getClass().getSimpleName(), is(expectedClassName));
    }
}
