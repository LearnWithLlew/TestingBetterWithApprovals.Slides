package org.learnwithllew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot {
    private final BotOutput output;
    private final Map<String, BotAction> botReactions = new HashMap<>();

    public Bot(BotOutput output) {
        this.output = output;
        botReactions.put("hi", new BotAction(List.of(
            new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
            new SendPlainMessageCommand("What would you like to do today?")
        )));
    }

    public void receive(EventNotification message) {
        String conversationId = message.getConversationId();
        List<BotAction> events = message.getEvents().stream().map(event -> botReactions.get(event.message())).toList();
        events.forEach(botAction -> output.send(conversationId, botAction));
    }
}
