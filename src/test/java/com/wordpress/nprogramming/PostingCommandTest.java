package com.wordpress.nprogramming;

import com.wordpress.nprogramming.commands.Command;
import com.wordpress.nprogramming.commands.PostingCommand;
import com.wordpress.nprogramming.model.Timeline;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PostingCommandTest {

    private Command command;

    @Before
    public void setUp() throws Exception {
        command = new PostingCommand();
    }

    @Test
    public void test_empty_app_context() throws Exception {
        TwitterAppContext context = new TwitterAppContext();

        Timeline timeline = context.getTimelineFor(CommandSamples.MIKOLAJ);
        assertThat(timeline, is(Timeline.Empty.INSTANCE));
    }

    @Test
    public void test_posting_first_time_command_results_in_creating_user_timeline() throws Exception {
        TwitterAppContext context = new TwitterAppContext();
        command.run(CommandSamples.POSTING_COMMAND, context);

        Timeline timeline = context.getTimelineFor(CommandSamples.MIKOLAJ);
        assertThat(timeline.getOwner(), is(CommandSamples.MIKOLAJ));
        assertThat(timeline.getMessages().size(), is(1));
    }

    @Test
    public void test_posting_two_commands_which_should_result_in_creating_user_timeline_with_two_messages() throws Exception {
        TwitterAppContext context = new TwitterAppContext();
        command.run(CommandSamples.POSTING_COMMAND, context);
        command.run(CommandSamples.POSTING_COMMAND, context);

        Timeline timeline = context.getTimelineFor(CommandSamples.MIKOLAJ);
        assertThat(timeline.getOwner(), is(CommandSamples.MIKOLAJ));
        assertThat(timeline.getMessages().size(), is(2));
    }

    @Test
    public void test_posting_two_commands_with_different_users_which_should_result_in_creating_two_user_timelines() throws Exception {
        TwitterAppContext context = new TwitterAppContext();
        command.run(CommandSamples.POSTING_COMMAND, context);
        command.run(CommandSamples.POSTING_COMMAND_2, context);

        Timeline timeline = context.getTimelineFor(CommandSamples.MIKOLAJ);
        assertThat(timeline.getOwner(), is(CommandSamples.MIKOLAJ));
        assertThat(timeline.getMessages().size(), is(1));

        timeline = context.getTimelineFor(CommandSamples.JULIA);
        assertThat(timeline.getOwner(), is(CommandSamples.JULIA));
        assertThat(timeline.getMessages().size(), is(1));
    }
}
