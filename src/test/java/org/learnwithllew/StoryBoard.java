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
        } else if (command instanceof TransferConversationCommand transferCommand) {
            return String.format("[     Bot]: transfers to destination '%s'\n", transferCommand.destination());
        } else {
            return String.format("[     Bot]: %s\n", command);
        }
    }

}
