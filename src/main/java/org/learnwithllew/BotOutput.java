package org.learnwithllew;

import java.util.HashMap;
import java.util.Map;

public class BotOutput {
    private final Map<String, BotAction> messages = new HashMap<>();

    public void send(String conversationId, BotAction action) {
        messages.put(conversationId, action);
    }

    public BotAction read(String conversationId) {
        return messages.get(conversationId);
    }
}

