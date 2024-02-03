package org.learnwithllew;

import org.apache.commons.lang3.mutable.MutableInt;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

@Disabled("not implemented yet")
public class A3Test {

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
            conversation("pay bill", "i like coffee", "i like tea", "no"),
            conversation("pay bill", "Yes, I'm a customer")
                .and("pay bill"),
            conversation("pay bill", "No, I'm not")
                .and("pay bill"),

            conversation("talk to an operator"),

            conversation("oh hi there, how are you doing"),
            conversation("walk my dog", "Yes, I'm a customer"),
            conversation("pay bill")
                .withCustomer(CustomerType.EXISTING)
                .duringWorkHours()
                .and("pay bill")
                .duringOffHours()
        );

        MutableInt counter = new MutableInt(0);
        Approvals.verifyAll("Chatbot conversations", conversations, c -> haveConversation(c, counter));
    }

    private String haveConversation(Conversation convo, MutableInt counter) {
        return "";
    }

    private Conversation conversation(String... messages) {
        return new Conversation(messages);
    }
}
