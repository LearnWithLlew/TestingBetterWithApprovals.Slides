package org.learnwithllew;

import org.learnwithllew.helper.HardcodedBotResponses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot {
    private final HardcodedBotResponses hardcodedResponses = new HardcodedBotResponses();
    private final BotOutput output;
    private final Map<String, List<String>> receivedMessages = new HashMap<>();

    public Bot(BotOutput output) {
        this.output = output;
    }

    public void receive(EventNotification event) {
        recordMessage(event);
        List<String> messagesSoFar = receivedMessages.get(event.getConversationId());
        BotAction response = hardcodedResponses.search(messagesSoFar);
        if (response == null) {
            throw new RuntimeException("Not implemented. Bot does not have a reaction to a given chain of messages: " +
                messagesSoFar +
                ". \nConsider adding in HardcodedBotResponses.java");
        }
        output.send(event.getConversationId(), response);
    }

    private void recordMessage(EventNotification event) {
        receivedMessages.computeIfAbsent(event.getConversationId(), it -> new ArrayList<>());
        List<String> messagesSoFar = receivedMessages.get(event.getConversationId());
        event.getEvents().forEach(m -> messagesSoFar.add(m.message()));
    }
}
