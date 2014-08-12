package com.wordpress.nprogramming;

import com.wordpress.nprogramming.commands.Command;
import com.wordpress.nprogramming.commands.PostingCommand;
import com.wordpress.nprogramming.commands.ReadingCommand;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ReadingCommandTest {
    private Command command;
    private AppContext context;

    @Before
    public void setUp() throws Exception {
        command = new ReadingCommand();
        context = new TwitterAppContext();
        new PostingCommand().run(CommandSamples.POSTING_COMMAND_FOR_MIKOLAJ, context);
    }

    @Test
    public void test_that_reading_from_non_existing_user_results_in_empty_output() throws Exception {
        String result = command.run("NonExistingUser", context);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void test_that_reading_from_existing_user_results_produce_valid_output() throws Exception {
        String result = command.run(CommandSamples.MIKOLAJ, context);
        assertThat(result, is("\n" +
                "Hello dad! (0 minutes ago)"));
    }

    @Test
    public void test_that_reading_data_for_user_with_more_than_one_message_produce_valid_output() throws Exception {
        new PostingCommand().run(CommandSamples.POSTING_COMMAND_FOR_MIKOLAJ, context);
        String result = command.run(CommandSamples.MIKOLAJ, context);
        assertThat(result, is("\n" +
                "Hello dad! (0 minutes ago)\n" +
                "Hello dad! (0 minutes ago)"));
    }
}
