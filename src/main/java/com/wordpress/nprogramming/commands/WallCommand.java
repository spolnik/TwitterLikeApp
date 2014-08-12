package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;
import com.wordpress.nprogramming.model.Message;
import com.wordpress.nprogramming.model.Timeline;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkState;

public class WallCommand extends CommandBase {

    private static final Pattern REGEX = Pattern.compile("^(\\S+) wall$");

    @Override
    public String run(String rawCommand, TwitterAppContext context) {
        final Matcher matcher = REGEX.matcher(rawCommand);
        checkState(matcher.find(), "Raw command input is wrong as posting command cannot handle it");

        String user = matcher.group(1);

        List<Timeline> timelines = context.getAllTimelines(user);

        List<Message> allMessages = new ArrayList<>();

        for (Timeline timeline : timelines) {
            allMessages.addAll(timeline.getMessages());
        }

        Collections.sort(allMessages, (message1, message2) -> message1.getCreatedOn().compareTo(message2.getCreatedOn()));

        StringBuilder output = new StringBuilder();

        for (Message message : allMessages) {
            final int minutes = Minutes.minutesBetween(DateTime.now(), message.getCreatedOn()).getMinutes();
            final String minutesPart = minutes == 1 ? "minute" : "minutes";

            output.append(String.format("\n%s - %s (%d %s ago)", message.getOwner(), message.getValue(), minutes, minutesPart));
        }

        return output.toString();
    }

    @Override
    protected Pattern getCommandRegex() {
        return REGEX;
    }
}
