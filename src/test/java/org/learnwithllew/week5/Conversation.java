package org.learnwithllew.week5;

public class Conversation {

    public String[] messages;

    public Conversation(String... conversations) {
        messages = conversations;
    }

    public String printMessages() {
        return "[" + String.join(", ", this.messages) + "]";
    }
}
