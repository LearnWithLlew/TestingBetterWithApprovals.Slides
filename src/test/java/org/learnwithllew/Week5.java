package org.learnwithllew;

import org.apache.commons.lang3.mutable.MutableInt;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.learnwithllew.week3.StoryBoard;
import org.learnwithllew.week5.Conversations;

import java.util.List;

// dual conversations (.and) -> some can’t convert v2 -> setting state
public class Week5 {

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
            conversation("pay bill"),
            conversation("pay bill", "I like swimming"),
            conversation("pay bill", "I like coffee", "I like tea", "no"),
            conversation("pay bill", "Yes, I'm a customer")
                .thenLater("pay bill"),
            conversation("pay bill", "No, I'm not")
                .thenLater("pay bill"),

            conversation("talk to an operator"),
            conversation("oh hi there, how are you doing"),
            conversation("walk my dog", "Yes, I'm a customer")
//            conversation("pay bill")
//                .withCustomer(CustomerType.EXISTING)
//                .duringWorkHours()
//                .and("pay bill")
//                .duringOffHours()
        );

        MutableInt counter = new MutableInt(0);
        Approvals.verifyAll("Chatbot conversations", conversations, c -> haveConversation(c, counter));
    }

    private String haveConversation(Conversations conversations, MutableInt counter) {
        counter.increment();
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);

        var storyBoard = "# Scenario %s: %s\n".formatted(counter, conversations.printMessages());
        for (int i = 0; i < conversations.conversations.size(); i++) {
            var conversation = conversations.conversations.get(i);
            var messages = conversation.messages;
            storyBoard += String.format("%s***** Conversation %s *****\n", i == 0 ? "" : "\n", i + 1);
            storyBoard += StoryBoard.create(bot, output, messages);
        }
        return storyBoard;
    }

    private Conversations conversation(String... messages) {
        return new Conversations(messages);
    }
}