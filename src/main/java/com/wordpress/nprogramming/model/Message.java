package com.wordpress.nprogramming.model;

import org.joda.time.DateTime;

public class Message {
    private final String value;
    private final String owner;
    private final DateTime createdOn;

    public Message(String value, String owner) {
        this.value = value;
        this.owner = owner;
        createdOn = DateTime.now();
    }

    public String getValue() {
        return value;
    }

    public DateTime getCreatedOn() {
        return createdOn;
    }

    public String getOwner() {
        return owner;
    }
}
