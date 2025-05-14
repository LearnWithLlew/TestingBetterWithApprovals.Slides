package org.learnwithllew.week5;

import java.util.ArrayList;

public class MultipleConversations extends ArrayList<Conversation> {

    public MultipleConversations(String... messages) {
        this.thenConversation(messages);
    }

    public MultipleConversations thenConversation(String... messages) {
        add(new Conversation(messages));
        return this;
    }
}
