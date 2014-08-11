package com.wordpress.nprogramming;

import com.wordpress.nprogramming.model.EmptyTimeline;
import com.wordpress.nprogramming.model.Timeline;

public class TwitterAppContext {
    public Timeline getTimelineFor(String user) {
        return EmptyTimeline.INSTANCE;
    }
}
