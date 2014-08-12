package com.wordpress.nprogramming;

import com.wordpress.nprogramming.commands.FollowingCommand;
import com.wordpress.nprogramming.commands.PostingCommand;
import com.wordpress.nprogramming.commands.WallCommand;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WallCommandTest {
    private WallCommand command;
    private AppContext context;

    @Before
    public void setUp() throws Exception {
        context = new TwitterAppContext();
        command = new WallCommand();
        new PostingCommand().run(CommandSamples.POSTING_COMMAND_FOR_MIKOLAJ, context);
        new PostingCommand().run(CommandSamples.POSTING_COMMAND_FOR_JULIA, context);
        new FollowingCommand().run(CommandSamples.FOLLOWING_COMMAND_JULIA_FOLLOWS_MIKOLAJ, context);
    }

    @Test
    public void test_displaying_wall_of_user_with_one_subscription_should_result_in_all_messages_displayed() throws Exception {
        String result = command.run(CommandSamples.WALL_COMMAND_FOR_JULIA, context);
        assertThat(result, is("\n" +
                "Julia - Good morning dad! (0 seconds ago)\n" +
                "Mikolaj - Hello dad! (0 seconds ago)\n"));
    }
}