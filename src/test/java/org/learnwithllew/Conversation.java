package org.learnwithllew;

public class Conversation {
    private final String[] messages;

    public Conversation(String[] messages) {
        this.messages = messages;
    }

    public Conversation and(String message) {
        return this;
    }

    public Conversation withCustomer(CustomerType customerType) {
        return this;
    }

    public Conversation duringWorkHours() {
        return this;
    }

    public Conversation duringOffHours() {
        return this;
    }
}
