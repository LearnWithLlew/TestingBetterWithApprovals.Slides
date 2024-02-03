package org.learnwithllew;

import org.learnwithllew.helper.ExistingCustomerResponses;
import org.learnwithllew.helper.HardcodedBotResponses;
import org.learnwithllew.helper.ProspectiveCustomerResponses;
import org.learnwithllew.helper.UnknownCustomerResponses;

import java.sql.SQLOutput;
import java.util.*;

public class Bot {
    private final EnumMap<CustomerType, HardcodedBotResponses> responses = new EnumMap<>(CustomerType.class);
    private final BotOutput output;
    private final Map<String, List<String>> receivedMessages = new HashMap<>();

    private CustomerType customerType = CustomerType.UNKNOWN;

    public Bot(BotOutput output) {
        this.output = output;
        responses.put(CustomerType.UNKNOWN, new UnknownCustomerResponses());
        responses.put(CustomerType.EXISTING, new ExistingCustomerResponses());
        responses.put(CustomerType.PROSPECTIVE, new ProspectiveCustomerResponses());
    }

    public void receive(EventNotification event) {
        recordMessage(event);
        List<String> messagesSoFar = receivedMessages.get(event.getConversationId());

        BotAction response = handleEvent(messagesSoFar);
        output.send(event.getConversationId(), response);
        rememberCustomerType(event);
    }

    private BotAction handleEvent(List<String> messagesSoFar) {
        HardcodedBotResponses botResponses = getHardcodedResponses(customerType);
        BotAction response = botResponses.search(messagesSoFar);
        if (response == null) {
            throw new RuntimeException("Not implemented. Bot does not have a reaction to a given chain of messages: " +
                messagesSoFar +
                ". \nConsider adding in " + botResponses.getClass().getSimpleName() + ".java");
        }
        return response;
    }

    private HardcodedBotResponses getHardcodedResponses(CustomerType customerType) {
        return responses.get(customerType);
    }

    private void recordMessage(EventNotification event) {
        receivedMessages.computeIfAbsent(event.getConversationId(), it -> new ArrayList<>());
        List<String> messagesSoFar = receivedMessages.get(event.getConversationId());
        event.getEvents().forEach(m -> messagesSoFar.add(m.message()));
    }

    private void rememberCustomerType(EventNotification event) {
        boolean isCustomerResponse = event.getEvents().stream().anyMatch(it -> it.message().contains("Yes, I'm a customer"));
        if (isCustomerResponse) {
            customerType = CustomerType.EXISTING;
        }

        boolean isNoResponse = event.getEvents().stream().anyMatch(it -> it.message().equals("no") || it.message().equals("No, I'm not"));
        if (isNoResponse) {
            customerType = CustomerType.PROSPECTIVE;
        }
    }
}
