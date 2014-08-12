package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.AppContext;
import com.wordpress.nprogramming.model.Message;
import com.wordpress.nprogramming.model.Timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkState;

public class ReadingCommand extends OutputCommandBase {

    private static final Pattern REGEX = Pattern.compile("^\\S+$");

    @Override
    public String run(final String rawCommand, final AppContext context) {
        final Matcher matcher = REGEX.matcher(rawCommand);
        checkState(matcher.find(), "Raw command input is wrong as posting command cannot handle it");

        final Timeline timeline = context.getTimelineFor(rawCommand);

        if (timeline == Timeline.Empty.INSTANCE)
            return null;

        return buildOutputMessage(timeline);
    }

    private String buildOutputMessage(Timeline timeline) {
        final StringBuilder output = new StringBuilder();

        List<Message> sortedMessages = new ArrayList<>(timeline.getMessages());
        sortMessages(sortedMessages);

        for (Message message : sortedMessages) {
            String timePart = createTimePart(message);
            output.append(String.format("\n%s %s", message.getValue(), timePart));
        }

        output.append("\n");
        return output.toString();
    }

    @Override
    protected Pattern getCommandRegex() {
        return REGEX;
    }
}
