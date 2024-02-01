package org.learnwithllew;

import java.util.List;

public class BotAction {
    private final List<Command> commands;

    public BotAction(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public String getCommandLog1() {
        return """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            """;
    }
}
