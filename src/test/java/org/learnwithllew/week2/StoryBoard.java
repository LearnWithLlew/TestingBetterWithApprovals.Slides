package org.learnwithllew.week2;

import org.learnwithllew.*;

public class StoryBoard {

    public static int conversationId = 0;

    public static String create(Bot bot, BotOutput botOutput, String... messages) {
        int currentConversationId = conversationId++;

        StringBuilder board = new StringBuilder();
        for (int i = 0; i < messages.length; i++) {
            board.append(String.format("[Customer]: %s\n", messages[i]));
            EventNotification initialMessage = TestUtils.messageFromCustomer(currentConversationId, "" + i, messages[i]).build();
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
            return String.format("[Bot]: %s\n", plainMessage.message());
        } else if (command instanceof SendQuestionCommand questionCommand) {
            return printJson(questionCommand);
        } else if (command instanceof TransferConversationCommand transferCommand) {
            return String.format("[Bot]: transfers to '%s'\n", transferCommand.destination());
        } else {
            return String.format("[Bot]: %s\n", command);
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

}
