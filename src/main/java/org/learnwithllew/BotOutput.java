package org.learnwithllew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotOutput {
    // In-memory storage for BotActions keyed by conversationId
    private Map<String, BotAction> outboundMessages = new HashMap<>();


    public BotOutput() {
        outboundMessages.put("Hi", new BotAction(List.of(
            new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
            new SendPlainMessageCommand("What would you like to do today?")
        )));
    }
    /**
     * Simulates sending a message by storing it in the outboundMessages map.
     *
     * @param conversationId The ID of the conversation.
     * @param action The BotAction to store.
     */
    public void send(String conversationId, BotAction action) {
        outboundMessages.put(conversationId, action);
    }

    /**
     * Reads the BotAction for a given conversationId.
     *
     * @param conversationId The ID of the conversation to read the BotAction for.
     * @return The BotAction associated with the conversationId.
     */
    public BotAction read(String conversationId) {
        return outboundMessages.get("Hi");
    }
}

