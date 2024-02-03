package org.learnwithllew;

import org.lambda.query.Queryable;

import java.util.List;
import java.util.UUID;

public class EventNotification {
    public static final Queryable<EventNotification> conversations = new Queryable<>(EventNotification.class);
    private String conversationId;
    private UserId userId;
    private List<Property> properties;
    private Queryable<MessageEvent> events;

    private EventNotification(UserId userId, List<Property> properties, Queryable<MessageEvent> events, String conversationId) {
        this.userId = userId;
        this.properties = properties;
        this.events = events;
        this.conversationId = conversationId;
        conversations.add(this);
    }

    public static EventNotification getByConversationId(String conversationId) {
        return conversations.first(e -> e.conversationId.equals(conversationId));
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

    public String getConversations() {
        String conversation = events.select(e -> e.message()).join(", ");
        return conversation;
    }

    // Builder class
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

