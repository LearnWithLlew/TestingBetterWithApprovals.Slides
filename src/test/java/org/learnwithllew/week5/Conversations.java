package org.learnwithllew.week5;

import org.lambda.query.Queryable;
import org.learnwithllew.CustomerType;

import java.util.Arrays;

public class Conversations {

    public static class Conversation {

        public String[] messages;

        public Conversation(String[] messages) {
            this.messages = messages;
        }

        @Override
        public String toString() {
            return "Conversation{" +
                "messages=" + Arrays.toString(messages) +
                '}';
        }
    }

    public CustomerType customerType = CustomerType.UNKNOWN;
    public final Queryable<Conversation> conversations = new Queryable<>();

    public static Conversations conversation(String... messages) {
        return new Conversations(messages);
    }

    public Conversations(String... conversations) {
        this.conversations.add(new Conversation(conversations));
    }

    public Conversations and(String... messages) {
        this.conversations.add(new Conversation(messages));
        return this;
    }

    public String printMessages() {
        return String.join(", ",
            conversations.stream()
                .map(c -> "[" + String.join(", ", c.messages) + "]")
                .toArray(String[]::new));
    }

    public Conversations duringOffHours() {
        return this;
    }

    public Conversations duringWorkHours() {
        return this;
    }

    public Conversations withCustomer(CustomerType customerType) {
        this.customerType = customerType;
        return this;
    }
}
