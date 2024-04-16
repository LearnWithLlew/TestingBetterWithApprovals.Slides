package org.learnwithllew.week7;

import com.spun.util.Tuple;
import org.apache.commons.lang3.mutable.MutableInt;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;
import org.lambda.query.Queryable;
import org.learnwithllew.Bot;
import org.learnwithllew.BotOutput;
import org.learnwithllew.week5.Conversation;

// when everything is done
public class Week7 {

    @Test
    void testConversations() {
        MutableInt i = new MutableInt(0);
        var conversations = Queryable.as(
            conversation("hi"),
            conversation("hi", "hi"),
            conversation("hi", "hi", "hi"),
            conversation("hi", "pay bill"),
            conversation("hi", "pay bill", "Yes, I'm a customer"),
            conversation("hi", "pay bill", "no"),
            conversation("hi", "talk to an operator", "Yes, I'm a customer"),
            conversation("hi", "talk to an operator", "no"),
            conversation("oh hi there, how are you doing"),
            conversation("pay bill"),
            conversation("pay bill", "I like swimming"),
            conversation("pay bill", "I like coffee", "I like tea", "no"),
            conversation("walk my dog", "Yes, I'm a customer"),
            conversation("talk to an operator"),
            conversation("hi, can you please give me the special menu?"),
            conversation("pay bill", "Yes, I'm a customer")
                .thenConversation("pay bill"),
            conversation("pay bill", "No, I'm not")
                .thenConversation("pay bill")
        ).select(c -> new Tuple<>(i.incrementAndGet(), c));

        Approvals.verifyAll(createHeader(conversations), conversations, c -> haveConversations(c), new Options().forFile().withExtension(".md"));
    }

    private String createHeader(Queryable<Tuple<Integer, MultipleConversations>> conversations) {
        var header = "# Chatbot conversations\n\n";
        header += conversations
            .select(t -> String.format("1. [%s](#scenario-%s)", t.getSecond().join(",", c -> c.printMessages()), t.getFirst()))
            .join("\n");
        return header;
    }

    private String haveConversations(Tuple<Integer, MultipleConversations> conversations) {

        String conversationText = conversations.getSecond().select(Conversation::printMessages).join(", ");
        var storyBoard = """
            ### Scenario %s
            #### %s
            ```
            %s
            ```
            ---
            """.formatted(conversations.getFirst(), conversationText, printConversations(conversations.getSecond()));


        return storyBoard;
    }

    private String printConversations(MultipleConversations conversations) {
        var storyBoard = "";
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
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

    private class MultipleConversations extends Queryable<Conversation> {
        public MultipleConversations(Conversation conversations) {
            add(conversations);
        }

        public MultipleConversations thenConversation(String... messages) {
            add(new Conversation(messages));
            return this;
        }
    }
}
