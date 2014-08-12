package com.wordpress.nprogramming;

import com.google.common.base.Strings;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.wordpress.nprogramming.commands.Command;
import com.wordpress.nprogramming.commands.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

final class TwitterApp {

    private final List<Command> commands;
    private final AppContext context;
    private final CommandParser commandParser;

    private TwitterApp() {
        Injector injector = Guice.createInjector(new TwitterAppModule());

        commandParser = injector.getInstance(CommandParser.class);
        context = injector.getInstance(AppContext.class);
        commands = injector.getInstance(Key.get(new TypeLiteral<List<Command>>() {}));
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        prompt();
        String rawCommand = reader.readLine();

        final TwitterApp twitterApp = new TwitterApp();

        while (!Strings.isNullOrEmpty(rawCommand)) {
            final String result = twitterApp.processCommand(rawCommand);

            if (!Strings.isNullOrEmpty(result))
                System.out.println(result);

            prompt();
            rawCommand = reader.readLine();
        }

        System.out.print("Chat is closing... Press <enter> ...");
        reader.readLine();
    }

    private static void prompt() {
        System.out.print("> ");
    }

    private String processCommand(final String rawCommand) {
        final Command command = commandParser.parse(rawCommand, commands);
        return command.run(rawCommand, context);
    }
}
