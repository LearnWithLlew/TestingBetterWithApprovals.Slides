package org.learnwithllew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotOutput {
    private final Map<String, BotAction> outboundMessages = new HashMap<>();

    public BotOutput() {
        outboundMessages.put("hi", new BotAction(List.of(
            new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
            new SendPlainMessageCommand("What would you like to do today?")
        )));
    }

    public void send(String conversationId, BotAction action) {
        outboundMessages.put(conversationId, action);
    }

    public BotAction read(String conversationId) {
        return outboundMessages.get(EventNotification.getByConversationId(conversationId).getConversations());
    }
}

