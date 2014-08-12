package com.wordpress.nprogramming.commands;

import com.wordpress.nprogramming.model.Message;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import java.util.Collections;
import java.util.List;

abstract class OutputCommandBase extends CommandBase {
    void sortMessages(List<Message> messages) {
        Collections.sort(messages,
                (message1, message2) -> message2.getCreatedOn().compareTo(message1.getCreatedOn()));
    }

    String createTimePart(Message message) {
        int valueBetween = Minutes.minutesBetween(message.getCreatedOn(), DateTime.now()).getMinutes();
        String wordPart;

        if (valueBetween > 0) {
            wordPart = "minute";
        } else {
            valueBetween = Seconds.secondsBetween(message.getCreatedOn(), DateTime.now()).getSeconds();
            wordPart = "second";
        }

        return String.format("(%d %s%s ago)", valueBetween, wordPart, pluralizeIfManyOrZero(valueBetween));
    }

    private String pluralizeIfManyOrZero(int value) {
        return value == 1 ? "" : "s";
    }
}
