package org.learnwithllew;

import java.util.List;

public class LoggerUtils {

    public static String printCommands(List<Command> commands) {
        String output = "";
        for (Command command : commands) {
            String result = "";
            if (command instanceof SendPlainMessageCommand plainMessage) {
                result = String.format("[Bot]: %s\n", plainMessage.message());
            }
            output += result;
        }
        return output;
    }
}
