package org.learnwithllew;

public class StoryBoard {

    public static String create(Bot bot, BotOutput botOutput, String... messages) {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < messages.length; i++) {
            board.append(String.format("[Customer]: %s\n", messages[i]));
            EventNotification initialMessage = TestUtils.messageFromCustomer("" + i, messages[i]).build();
            bot.receive(initialMessage);
            BotAction actions = botOutput.read(initialMessage.getConversationId());
            for (Command command : actions.commands()) {
                board.append(printCommand(command));
            }
        }
        return board.toString();
    }

    private static String printCommand(Command command) {
        if (command instanceof SendPlainMessageCommand plainMessage) {
            return String.format("[     Bot]: %s\n", plainMessage.message());
        } else if (command instanceof SendQuestionCommand questionCommand) {
            return prettyPrint(questionCommand);
        } else if (command instanceof TransferConversationCommand transferCommand) {
            return String.format("[     Bot]: transfers to '%s'\n", transferCommand.destination());
        } else {
            return String.format("[     Bot]: %s\n", command);
        }
    }

    private static String prettyPrint(SendQuestionCommand command) {
        String output = "";
        String header = "[     Bot]: ";
        String blank = "[        ]: ";
        output = header + command.question() + "\n";
        int i = 1;
        for (String answer : command.answers()) {
            output += String.format("%s  %s) %s\n", blank, i++, answer);
        }

        return output;
    }


}
