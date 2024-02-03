package org.learnwithllew;

import java.util.List;

public record BotAction(List<Command> commands) {

    public String getCommandLog1() {
        return """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            """;
    }
}
