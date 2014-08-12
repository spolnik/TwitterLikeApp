package com.wordpress.nprogramming;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.wordpress.nprogramming.commands.Command;
import com.wordpress.nprogramming.commands.CommandParser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserAcceptanceTest {
    private List<Command> commands;
    private AppContext context;
    private CommandParser commandParser;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new TwitterAppModule());

        commandParser = injector.getInstance(CommandParser.class);
        context = injector.getInstance(AppContext.class);
        commands = injector.getInstance(Key.get(new TypeLiteral<List<Command>>() {}));
    }

    @Ignore("Long running user acceptance test")
    @Test
    public void user_acceptance_test() throws Exception {

        // Posting: Alice can publish messages to a personal timeline

        processCommand("Alice -> I love the weather today");
        Thread.sleep(1000*40);
        processCommand("Bob -> at least it's sunny");
        Thread.sleep(1000*10);
        processCommand("Bob -> Oh, we lost!");
        Thread.sleep(1000*10);

        // Reading: Bob can view Alice’s timeline
        String result = processCommand("Alice");
        assertThat(result, is("\n" +
                "I love the weather today (1 minute ago)\n"));

        result = processCommand("Bob");
        assertThat(result, is("\n" +
                "Oh, we lost! (10 seconds ago)\n" +
                "at least it's sunny (20 seconds ago)\n"));

        // Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view them on his wall, an aggregated list of all subscriptions

        processCommand("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
        Thread.sleep(1000*2);
        processCommand("Charlie follows Alice");

        result = processCommand("Charlie wall");
        assertThat(result, is("\n" +
                "Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)\n" +
                "Alice - I love the weather today (1 minute ago)\n"));

        processCommand("Charlie follows Bob");
        result = processCommand("Charlie wall");
        assertThat(result, is("\n" +
                "Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)\n" +
                "Bob - Oh, we lost! (12 seconds ago)\n" +
                "Bob - at least it's sunny (22 seconds ago)\n" +
                "Alice - I love the weather today (1 minute ago)\n"));
    }

    private String processCommand(String rawCommand) {
        Command command = commandParser.parse(rawCommand, commands);
        return command.run(rawCommand, context);
    }
}
