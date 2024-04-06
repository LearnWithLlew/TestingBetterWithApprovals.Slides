package org.learnwithllew.week6;

import org.apache.commons.lang3.StringUtils;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.learnwithllew.Bot;
import org.learnwithllew.BotOutput;
import org.learnwithllew.week3.StoryBoard;
import org.learnwithllew.week5.Conversation;

import java.util.ArrayList;
import java.util.List;

import static org.lambda.query.Query.select;

// when everything is done
public class Week6 {

    @Test
    void testConversations() {
        var conversations = List.of(
            conversation("hi"),
            conversation("hi", "hi"),
            conversation("hi", "hi", "hi"),
            conversation("hi", "pay bill"),
            conversation("hi", "pay bill", "Yes, I'm a customer"),
            conversation("hi", "pay bill", "no"),
            conversation("hi", "talk to an operator", "Yes, I'm a customer"),
            conversation("oh hi there, how are you doing"),
            conversation("pay bill"),
            conversation("pay bill", "I like swimming"),
            conversation("pay bill", "I like coffee", "I like tea", "no"),
            conversation("walk my dog", "Yes, I'm a customer"),
            conversation("talk to an operator"),
            conversation("pay bill", "Yes, I'm a customer")
                .thenConversation("pay bill"),
            conversation("pay bill", "No, I'm not")
                .thenConversation("pay bill")
        );

        Approvals.verifyAll("Chatbot conversations", conversations, c -> haveConversations(c));
    }

    private String haveConversations(List<Conversation> conversations) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);

        var separator = "****************************************************************\n";
        String conversationText = select(conversations, Conversation::printMessages).join(", ");
        var storyBoard = "%s* %s%s\n%s".formatted(separator, conversationText,
            StringUtils.leftPad("*", separator.length() - 3 - conversationText.length()), separator);
        for (int i = 0; i < conversations.size(); i++) {
            var conversation = conversations.get(i);
            if (1 < conversations.size()) {
                storyBoard += String.format("%s***** Conversation %s *****\n", i == 0 ? "" : "\n", i + 1);
            }
            storyBoard += StoryBoard.create(bot, output, conversation.messages);

        }
        return storyBoard;
    }

    private MultipleConversations conversation(String... messages) {
        return new MultipleConversations(new Conversation(messages));
    }

    private class MultipleConversations extends ArrayList<Conversation> {
        public MultipleConversations(Conversation conversations) {
            add(conversations);
        }

        public MultipleConversations thenConversation(String... messages) {
            add(new Conversation(messages));
            return this;
        }
    }
}
