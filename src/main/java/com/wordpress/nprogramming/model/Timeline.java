package com.wordpress.nprogramming.model;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

public class Timeline {
    private final String owner;
    private final List<Message> messages;

    public Timeline(String owner) {
        checkState(!Strings.isNullOrEmpty(owner.trim()), "Owner cannot be null nor empty string.");

        this.owner = owner;
        messages = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
