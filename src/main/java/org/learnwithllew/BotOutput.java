package org.learnwithllew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotOutput {
    private final Map<String, BotAction> outboundMessages = new HashMap<>();

    public void send(String conversationId, BotAction action) {
        outboundMessages.put(conversationId, action);
    }

    public BotAction read(String conversationId) {
        return outboundMessages.get(conversationId);
    }
}

