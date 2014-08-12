package com.wordpress.nprogramming;

import com.wordpress.nprogramming.commands.FollowingCommand;
import com.wordpress.nprogramming.commands.PostingCommand;
import com.wordpress.nprogramming.model.Timeline;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FollowingCommandTest {
    private FollowingCommand command;
    private TwitterAppContext context;

    @Before
    public void setUp() throws Exception {
        command = new FollowingCommand();
        context = new TwitterAppContext();
        new PostingCommand().run(CommandSamples.POSTING_COMMAND, context);
    }

    @Test
    public void test_that_subscribing_to_existing_user_adds_follower_to_that_timeline() throws Exception {
        command.run(CommandSamples.FOLLOWING_COMMAND, context);
        Timeline timeline = context.getTimelineFor(CommandSamples.MIKOLAJ);
        List<String> followers = timeline.getFollowers();

        assertThat(followers.contains(CommandSamples.JULIA), is(true));
    }

    @Test
    public void test_that_subscribing_follower_to_non_existing_user_do_nothing() throws Exception {
        command.run("Julia follows non_existing_user", context);
        Timeline timeline = context.getTimelineFor("non_existing_user");

        assertThat(timeline, is(Timeline.Empty.INSTANCE));
        List<String> followers = timeline.getFollowers();

        assertThat(followers.size(), is(0));
    }
}
