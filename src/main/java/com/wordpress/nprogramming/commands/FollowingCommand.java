package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.TwitterAppContext;
import com.wordpress.nprogramming.model.Timeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkState;

public class FollowingCommand extends CommandBase {

    private static final Pattern REGEX = Pattern.compile("^(\\S+) follows (\\S+)$");

    @Override
    public String run(String rawCommand, TwitterAppContext context) {
        final Matcher matcher = REGEX.matcher(rawCommand);
        checkState(matcher.find(), "Raw command input is wrong as posting command cannot handle it");

        String user = matcher.group(1);
        String userToFollow = matcher.group(2);

        Timeline timeline = context.getTimelineFor(userToFollow);

        if (timeline != Timeline.Empty.INSTANCE) {
            timeline.addFollower(user);
        }

        return null;
    }

    @Override
    protected Pattern getCommandRegex() {
        return REGEX;
    }
}
