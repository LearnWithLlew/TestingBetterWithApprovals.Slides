package org.learnwithllew.week4;

import org.learnwithllew.*;

public class StoryBoard {

    public static int conversationId = 0;

    public static String create(Bot bot, BotOutput output, Conversations conversations) {
        int currentConversationId = conversationId++;

        StringBuilder board = new StringBuilder();
        Conversations.Conversation conversation = conversations.conversations.first();
        for (int i = 0; i < conversation.messages.length; i++) {
            String message = conversation.messages[i];
            board.append(String.format("[Customer]: %s\n", message));
            EventNotification initialMessage = TestUtils.messageFromCustomer(currentConversationId, "" + i, message).build();
            bot.receive(initialMessage);
            BotAction actions = output.read(initialMessage.getConversationId());
            for (Command command : actions.commands()) {
                board.append(printCommand(command, false));
            }
        }
        return board.toString();
    }

    private static String printCommand(Command command, boolean useOriginalJson) {
        if (command instanceof SendPlainMessageCommand plainMessage) {
            return String.format("[     Bot]: %s\n", plainMessage.message());
        } else if (command instanceof SendQuestionCommand questionCommand) {
            return useOriginalJson ? printJson(questionCommand) : prettyPrint(questionCommand);
        } else if (command instanceof TransferConversationCommand transferCommand) {
            return String.format("[     Bot]: transfers to '%s'\n", transferCommand.destination());
        } else {
            return String.format("[     Bot]: %s\n", command);
        }
    }

    private static String printJson(SendQuestionCommand command) {
        var output = """
            [     Bot]: {
              "content": {
                "type": "vertical",
                "elements": [
                  {
                    "type": "text",
                    "text": "%s",
                    "tooltip": "text tooltip"
                  },
                  {
                    "type": "horizontal",
                    "elements": [
                      {
                        "type": "button",
                        "title": "Yes",
                        "click": {
                          "actions": [
                            {
                              "type": "publishText",
                              "text": "%s"
                            }
                          ]
                        }
                      },
                      {
                        "type": "button",
                        "title": "No",
                        "click": {
                          "actions": [
                            {
                              "type": "publishText",
                              "text": "%s"
                            }
                          ]
                        }
                      }
                    ]
                  }
                ]
              }
            }
            """;
        return String.format(output, command.question(), command.answers()[0], command.answers()[1]);
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
