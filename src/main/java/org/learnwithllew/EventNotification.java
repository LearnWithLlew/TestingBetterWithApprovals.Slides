package org.learnwithllew;

import java.util.List;

import java.util.UUID;

public class EventNotification {
    private UserId userId;
    private List<Property> properties;
    private List<MessageEvent> events;
    private String conversationId;

    // Private constructor
    private EventNotification(UserId userId, List<Property> properties, List<MessageEvent> events, String conversationId) {
        this.userId = userId;
        this.properties = properties;
        this.events = events;
        this.conversationId = conversationId;
    }

    // Getters
    public UserId getUserId() {
        return userId;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<MessageEvent> getEvents() {
        return events;
    }

    public String getConversationId() {
        return conversationId;
    }

    // Builder class
    public static class Builder {
        private UserId userId;
        private List<Property> properties;
        private List<MessageEvent> events;
        private String conversationId;

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder properties(List<Property> properties) {
            this.properties = properties;
            return this;
        }

        public Builder events(List<MessageEvent> events) {
            this.events = events;
            return this;
        }

        public EventNotification build() {
            this.conversationId = UUID.randomUUID().toString(); // Generate a random conversation ID
            return new EventNotification(userId, properties, events, conversationId);
        }
    }
}

