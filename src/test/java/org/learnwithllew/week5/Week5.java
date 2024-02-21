package org.learnwithllew.week5;

import org.apache.commons.lang3.StringUtils;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;
import org.learnwithllew.Bot;
import org.learnwithllew.BotOutput;
import org.learnwithllew.week3.StoryBoard;
import org.learnwithllew.week5.Conversations;

import java.util.List;

import static org.lambda.query.Query.select;

// dual conversations (.and) -> some can’t convert v2 -> setting state
public class Week5 {

    @Test
    void testConversations() {
        var conversations = List.of(
            conversation("hi"),
            conversation("hi", "pay bill"),
            conversation("pay bill", "I like coffee", "I like tea", "no"),
            conversation("hi", "talk to an operator", "Yes, I'm a customer"),
            conversation("pay bill"),
            conversation("hi", "hi"),
            conversation("hi", "hi", "hi"),
            conversation("walk my dog", "Yes, I'm a customer"),
            conversation("pay bill", "I like swimming"),
            conversation("talk to an operator"),
            conversation("hi", "pay bill", "Yes, I'm a customer"),
            conversation("oh hi there, how are you doing")
        );

        Approvals.verifyAll("Chatbot conversations", conversations, c -> haveConversation(c));
    }

    @Test
    void handleExistingCustomer() {
        var expected = """
            ****************************************************************
            * [pay bill, Yes, I'm a customer], [pay bill]                  *
            ****************************************************************
            ***** Conversation 1 *****
            [Customer]: pay bill
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: Yes, I'm a customer
            [     Bot]: transfers to 'self_service'
            
            ***** Conversation 2 *****
            [Customer]: pay bill
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: transfers to 'self_service'
            """;
        var conversation1 = conversation("pay bill", "Yes, I'm a customer");
        var conversation2 = conversation("pay bill");
        var conversations = List.of(conversation1, conversation2);

        Approvals.verify(haveConversations(conversations), new Options().inline(expected));
    }

    @Test
    void handleProspect() {
        var expected = """
            ****************************************************************
            * [pay bill, No, I'm not], [pay bill]                          *
            ****************************************************************
            ***** Conversation 1 *****
            [Customer]: pay bill
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: No, I'm not
            [     Bot]: transfers to 'operator'
            
            ***** Conversation 2 *****
            [Customer]: pay bill
            [     Bot]: Let me try to help you.
            [     Bot]: transfers to 'operator'
            """;
        var conversation1 = conversation("pay bill", "No, I'm not");
        var conversation2 = conversation("pay bill");
        var conversations = List.of(conversation1, conversation2);

        Approvals.verify(haveConversations(conversations), new Options().inline(expected));
    }

    private String haveConversation(Conversations conversations) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);

        var separator = "****************************************************************\n";
        var storyBoard = "%s* %s%s\n%s".formatted(separator, conversations.printMessages(), StringUtils.leftPad("*", separator.length() - 3 - conversations.printMessages().length()), separator);
        storyBoard += StoryBoard.create(bot, output, conversations.messages);
        return storyBoard;
    }

    private String haveConversations(List<Conversations> conversations) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);

        var separator = "****************************************************************\n";
        String conversationText = select(conversations, Conversations::printMessages).join(", ");
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

    private Conversations conversation(String... messages) {
        return new Conversations(messages);
    }
}
