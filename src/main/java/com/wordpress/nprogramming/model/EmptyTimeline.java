package com.wordpress.nprogramming.model;

public final class EmptyTimeline extends Timeline {
    private EmptyTimeline() {
        super("<empty>");
    }

    public static final Timeline INSTANCE = new EmptyTimeline();
}
