package org.learnwithllew.week5;

public class Conversations {

    public String[] messages;

    public Conversations(String... conversations) {
        messages = conversations;
    }

    public String printMessages() {
        return "[" + String.join(", ", this.messages) + "]";
    }
}
