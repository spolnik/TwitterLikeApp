package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.model.Message;

import java.util.Collections;
import java.util.List;

public abstract class OutputCommandBase extends CommandBase {
    protected void sortMessages(List<Message> messages) {
        Collections.sort(messages,
                (message1, message2) -> message2.getCreatedOn().compareTo(message1.getCreatedOn()));
    }
}
