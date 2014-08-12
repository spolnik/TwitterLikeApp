package com.wordpress.nprogramming;

import com.wordpress.nprogramming.model.Timeline;

import java.util.List;

public interface AppContext {
    Timeline getTimelineFor(String user);

    void addTimeline(Timeline timeline);

    List<Timeline> getAllTimelines(String user);
}
