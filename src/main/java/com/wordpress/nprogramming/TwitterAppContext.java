package com.wordpress.nprogramming;

import com.wordpress.nprogramming.model.Timeline;

import java.util.HashMap;
import java.util.Map;

public class TwitterAppContext {

    private final Map<String, Timeline> timelines = new HashMap<>();

    public Timeline getTimelineFor(String user) {
        if (timelines.containsKey(user)) {
            return timelines.get(user);
        }

        return Timeline.Empty.INSTANCE;
    }

    public void addTimeline(Timeline timeline) {
        timelines.put(timeline.getOwner(), timeline);
    }
}
