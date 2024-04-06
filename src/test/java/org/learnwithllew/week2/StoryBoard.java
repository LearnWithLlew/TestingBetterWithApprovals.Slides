package org.learnwithllew.week2;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;
import org.learnwithllew.*;

public class StoryBoard {

    private static final String CUSTOMER = "[Customer]:";
    private static final String BOT = "[Bot]:";
    private static final String TAB = "  ";
    public static int conversationId = 0;

    public static String create(Bot bot, BotOutput botOutput, String... messages) {
        int currentConversationId = conversationId++;

        StringBuilder board = new StringBuilder();
        for (int i = 0; i < messages.length; i++) {
            board.append(String.format("%s %s\n", CUSTOMER, messages[i]));
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
            return print(plainMessage);
        } else if (command instanceof SendQuestionCommand questionCommand) {
            return printJson(questionCommand);
        } else if (command instanceof TransferConversationCommand transferCommand) {
            return String.format("%s transfers to '%s'\n", BOT, transferCommand.destination());
        } else {
            return String.format("%s %s\n", BOT, command);
        }
    }

    private static String print(SendPlainMessageCommand plainMessage) {
        String[] lines = plainMessage.message().split("\n");
        String output = "";
        for (int i = 0; i < lines.length; i++) {
            if (i == 0) {
                output += String.format("%s %s\n", BOT, lines[i]);
            } else {
                output += String.format("%s %s\n", TAB, lines[i]);
            }
        }
        return output;
    }

    private static String printJson(SendQuestionCommand command) {
        var output = """
            %s {
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
        return String.format(output, BOT, command.question(), command.answers()[0], command.answers()[1]);
    }

}
