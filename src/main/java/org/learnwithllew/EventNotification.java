package org.learnwithllew;

import org.lambda.query.Queryable;

import java.util.List;

public class EventNotification {
    private final String conversationId;
    private final UserId userId;
    private final List<Property> properties;
    private final Queryable<MessageEvent> events;

    private EventNotification(UserId userId, List<Property> properties, Queryable<MessageEvent> events, String conversationId) {
        this.userId = userId;
        this.properties = properties;
        this.events = events;
        this.conversationId = conversationId;
    }

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

    public static class Builder {
        private String conversationId;
        private UserId userId;
        private List<Property> properties;
        private Queryable<MessageEvent> events;

        public Builder conversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder properties(List<Property> properties) {
            this.properties = properties;
            return this;
        }

        public Builder events(List<MessageEvent> events) {
            this.events = Queryable.as(events);
            return this;
        }

        public EventNotification build() {
            return new EventNotification(userId, properties, events, conversationId);
        }
    }
}

