package com.wordpress.nprogramming;

import com.wordpress.nprogramming.model.Timeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterAppContext implements AppContext {

    private final Map<String, Timeline> timelines = new HashMap<>();

    @Override
    public Timeline getTimelineFor(String user) {
        if (timelines.containsKey(user)) {
            return timelines.get(user);
        }

        return Timeline.Empty.INSTANCE;
    }

    @Override
    public void addTimeline(Timeline timeline) {
        timelines.put(timeline.getOwner(), timeline);
    }

    @Override
    public List<Timeline> getAllTimelines(String user) {
        List<Timeline> allTimelines = new ArrayList<>();

        for (String key : timelines.keySet()) {
            Timeline timeline = timelines.get(key);
            if (key.equals(user)) {
                allTimelines.add(timeline);
            } else if (timeline.getFollowers().contains(user)) {
                allTimelines.add(timeline);
            }
        }

        return allTimelines;
    }
}
