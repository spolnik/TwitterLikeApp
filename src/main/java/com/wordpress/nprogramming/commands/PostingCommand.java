package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;
import com.wordpress.nprogramming.model.Timeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkState;

public class PostingCommand extends CommandBase {

    private static final Pattern regex = Pattern.compile("^(\\S+) -> (.+)$");

    @Override
    public String run(String rawCommand, TwitterAppContext context) {
        Matcher matcher = regex.matcher(rawCommand);
        checkState(matcher.find(), "Raw command input is wrong as posting command cannot handle it");

        String user = matcher.group(1);
        String message = matcher.group(2);

        Timeline timeline = context.getTimelineFor(user);

        if (timeline == Timeline.Empty.INSTANCE) {
            context.addTimeline(new Timeline(user, message));
        } else {
            timeline.addMessage(message);
        }

        return null;
    }

    @Override
    protected Pattern getCommandRegex() {
        return regex;
    }
}
