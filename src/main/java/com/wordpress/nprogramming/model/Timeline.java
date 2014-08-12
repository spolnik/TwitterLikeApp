package com.wordpress.nprogramming.model;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

public class Timeline {
    private final String owner;
    private final List<Message> messages;
    private List<String> followers;

    public Timeline(String owner, String message) {
        checkState(!Strings.isNullOrEmpty(owner.trim()), "Owner cannot be null or empty.");
        checkState(!Strings.isNullOrEmpty(message.trim()), "Message cannot be null or empty.");

        this.owner = owner;

        messages = new ArrayList<>();
        messages.add(new Message(message, owner));

        followers = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(String value) {
        messages.add(new Message(value, owner));
    }

    public List<String> getFollowers() {
        return Collections.unmodifiableList(followers);
    }

    public void addFollower(String user) {
        followers.add(user);
    }

    public static final class Empty extends Timeline {
        private Empty() {
            super("<empty>", "<empty>");
        }

        public static final Timeline INSTANCE = new Empty();
    }
}
