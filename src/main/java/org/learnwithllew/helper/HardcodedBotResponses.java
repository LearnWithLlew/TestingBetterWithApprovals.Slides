package org.learnwithllew.helper;

import org.learnwithllew.BotAction;
import org.learnwithllew.SendPlainMessageCommand;
import org.learnwithllew.SendQuestionCommand;
import org.learnwithllew.TransferConversationCommand;

import java.util.List;

public class HardcodedBotResponses {

    private static final BotAction ARE_YOU_A_CUSTOMER = new BotAction(List.of(
        new SendPlainMessageCommand("Let me try to help you."),
        new SendQuestionCommand("Are you a customer?", "Yes, I'm a customer", "No, I'm not")));

    private final Trie knownResponses = new Trie();

    public HardcodedBotResponses() {
        knownResponses.insert(
            List.of("hi"),
            new BotAction(List.of(
                new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
                new SendPlainMessageCommand("What would you like to do today?"))));
        knownResponses.insert(
            List.of("hi", "hi"),
            new BotAction(List.of(
                new SendPlainMessageCommand("Hmmm, tell me a little more so I can help you.\n\nWhat would you like to do today?"))));
        knownResponses.insert(
            List.of("hi", "hi", "hi"),
            ARE_YOU_A_CUSTOMER);
        knownResponses.insert(
            List.of("hi", "pay bill"),
            ARE_YOU_A_CUSTOMER);
        knownResponses.insert(
            List.of("hi", "pay bill", "Yes, I'm a customer"),
            new BotAction(List.of(
                new TransferConversationCommand("self_service"))));
        knownResponses.insert(
            List.of("hi", "pay bill", "no"),
            new BotAction(List.of(
                new TransferConversationCommand("operator"))));
        knownResponses.insert(
            List.of("hi", "talk to an operator"),
            ARE_YOU_A_CUSTOMER);
        knownResponses.insert(
            List.of("hi", "talk to an operator", "Yes, I'm a customer"),
            new BotAction(List.of(
                new TransferConversationCommand("operator"))));

    }

    public BotAction search(List<String> messagesSoFar) {
        return knownResponses.search(messagesSoFar);
    }
}
