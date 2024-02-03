package org.learnwithllew;

import org.learnwithllew.helper.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot {
    private final Trie hardcodedResponses = new Trie();
    private final BotOutput output;
    private final Map<String, List<String>> receivedMessages = new HashMap<>();

    public Bot(BotOutput output) {
        this.output = output;

        hardcodedResponses.insert(
            List.of("hi"),
            new BotAction(List.of(
                new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
                new SendPlainMessageCommand("What would you like to do today?"))));
        hardcodedResponses.insert(
            List.of("hi", "hi"),
            new BotAction(List.of(
                new SendPlainMessageCommand("Hmmm, tell me a little more so I can help you.\n\nWhat would you like to do today?"))));
        hardcodedResponses.insert(
            List.of("hi", "hi", "hi"),
            new BotAction(List.of(
                new SendPlainMessageCommand("Let me try to help you."),
                new SendQuestionCommand("Are you a customer?", "Yes, I'm a customer", "No, I'm not"))));
    }

    public void receive(EventNotification event) {
        recordMessage(event);
        List<String> messagesSoFar = receivedMessages.get(event.getConversationId());
        BotAction response = hardcodedResponses.search(messagesSoFar);
        output.send(event.getConversationId(), response);
    }

    private void recordMessage(EventNotification event) {
        receivedMessages.computeIfAbsent(event.getConversationId(), it -> new ArrayList<>());
        List<String> messagesSoFar = receivedMessages.get(event.getConversationId());
        event.getEvents().forEach(m -> messagesSoFar.add(m.message()));
    }
}
