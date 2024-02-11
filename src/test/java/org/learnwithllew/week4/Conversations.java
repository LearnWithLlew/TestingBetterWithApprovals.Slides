package org.learnwithllew.week4;

import org.lambda.query.Queryable;

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

    public final Queryable<Conversation> conversations = new Queryable<>();

    public Conversations(String... conversations) {
        this.conversations.add(new Conversation(conversations));
    }
}
