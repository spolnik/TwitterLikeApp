package com.wordpress.nprogramming.model;

import org.joda.time.DateTime;

public class Message {
    private final String value;
    private DateTime createdOn;

    public Message(String value) {
        this.value = value;
        createdOn = DateTime.now();
    }

    public String getValue() {
        return value;
    }

    public DateTime getCreatedOn() {
        return createdOn;
    }
}
