package com.wordpress.nprogramming;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.wordpress.nprogramming.commands.*;

import java.util.ArrayList;
import java.util.List;

public class TwitterAppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CommandParser.class).to(SimpleCommandParser.class).asEagerSingleton();
        bind(new TypeLiteral<List<Command>>() {}).toProvider(CommandListProvider.class);
        bind(AppContext.class).to(TwitterAppContext.class).asEagerSingleton();
    }

    private static class CommandListProvider implements Provider<List<Command>> {

        @Override
        public List<Command> get() {
            List<Command> commands = new ArrayList<>();

            commands.add(new PostingCommand());
            commands.add(new ReadingCommand());
            commands.add(new FollowingCommand());
            commands.add(new WallCommand());

            return commands;
        }
    }
}
