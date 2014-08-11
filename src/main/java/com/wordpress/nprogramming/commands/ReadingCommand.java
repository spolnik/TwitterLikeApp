package com.wordpress.nprogramming.commands;

import com.google.common.base.Preconditions;
import com.wordpress.nprogramming.TwitterAppContext;
import com.wordpress.nprogramming.model.Message;
import com.wordpress.nprogramming.model.Timeline;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.inject.internal.util.$Preconditions.checkState;

public class ReadingCommand extends CommandBase {

    private static final Pattern regex = Pattern.compile("^\\S+$");

    @Override
    public String run(final String rawCommand, final TwitterAppContext context) {
        final Matcher matcher = regex.matcher(rawCommand);
        Preconditions.checkState(matcher.find(), "Raw command input is wrong as posting command cannot handle it");

        final Timeline timeline = context.getTimelineFor(rawCommand);

        if (timeline == Timeline.Empty.INSTANCE)
            return null;

        return buildOutputMessage(rawCommand, timeline);
    }

    private String buildOutputMessage(String rawCommand, Timeline timeline) {
        final StringBuilder output = new StringBuilder();

        for (Message message : timeline.getMessages()) {
            final int minutes = Minutes.minutesBetween(DateTime.now(), message.getCreatedOn()).getMinutes();
            final String minutesPart = minutes == 1 ? "minute" : "minutes";

            output.append(String.format("\n%s (%d %s ago)", message.getValue(), minutes, minutesPart));
        }

        return output.toString();
    }

    @Override
    protected Pattern getCommandRegex() {
        return regex;
    }
}
