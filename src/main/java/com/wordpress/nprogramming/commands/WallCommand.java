package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.AppContext;
import com.wordpress.nprogramming.model.Message;
import com.wordpress.nprogramming.model.Timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkState;

public class WallCommand extends OutputCommandBase {

    private static final Pattern REGEX = Pattern.compile("^(\\S+) wall$");

    @Override
    public String run(String rawCommand, AppContext context) {
        final Matcher matcher = REGEX.matcher(rawCommand);
        checkState(matcher.find(), "Raw command input is wrong as posting command cannot handle it");

        String user = matcher.group(1);

        List<Timeline> timelines = context.getAllTimelines(user);

        List<Message> allMessages = new ArrayList<>();

        for (Timeline timeline : timelines) {
            allMessages.addAll(timeline.getMessages());
        }

        sortMessages(allMessages);

        StringBuilder output = new StringBuilder();

        for (Message message : allMessages) {
            String timePart = createTimePart(message);
            output.append(String.format("\n%s - %s %s", message.getOwner(), message.getValue(), timePart));
        }

        output.append("\n");
        return output.toString();
    }

    @Override
    protected Pattern getCommandRegex() {
        return REGEX;
    }
}
