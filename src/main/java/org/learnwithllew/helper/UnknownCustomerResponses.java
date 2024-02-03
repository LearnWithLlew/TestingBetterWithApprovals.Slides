package org.learnwithllew.helper;

import org.learnwithllew.BotAction;
import org.learnwithllew.SendPlainMessageCommand;
import org.learnwithllew.SendQuestionCommand;
import org.learnwithllew.TransferConversationCommand;

import java.util.List;

public class UnknownCustomerResponses extends HardcodedBotResponses {

    public UnknownCustomerResponses() {
        knownResponses.insert(
            List.of("hi"),
            new BotAction(List.of(
                HI_I_AM_BOT,
                WHAT_WOULD_YOU_LIKE_TO_DO)));
        knownResponses.insert(
            List.of("hi", "hi"),
            new BotAction(List.of(
                new SendPlainMessageCommand("Hmmm, tell me a little more so I can help you.\n\nWhat would you like to do today?"))));
        knownResponses.insert(
            List.of("hi", "hi", "hi"),
            new BotAction(List.of(
                LET_ME_HELP_YOU,
                ARE_YOU_A_CUSTOMER_QUERY)));
        knownResponses.insert(
            List.of("hi", "pay bill"),
            new BotAction(List.of(
                LET_ME_HELP_YOU,
                ARE_YOU_A_CUSTOMER_QUERY)));
        knownResponses.insert(
            List.of("hi", "pay bill", "Yes, I'm a customer"),
            new BotAction(List.of(
                TRANSFER_TO_SELF_SERVICE)));
        knownResponses.insert(
            List.of("hi", "pay bill", "no"),
            new BotAction(List.of(
                TRANSFER_TO_HUMAN)));
        knownResponses.insert(
            List.of("hi", "talk to an operator"),
            new BotAction(List.of(
                LET_ME_HELP_YOU,
                ARE_YOU_A_CUSTOMER_QUERY)));
        knownResponses.insert(
            List.of("hi", "talk to an operator", "Yes, I'm a customer"),
            new BotAction(List.of(
                TRANSFER_TO_HUMAN)));
        knownResponses.insert(
            List.of("pay bill"),
            new BotAction(List.of(
                HI_I_AM_BOT,
                LET_ME_HELP_YOU,
                ARE_YOU_A_CUSTOMER_QUERY))
            );
        knownResponses.insert(
            List.of("pay bill", "I like swimming"),
            new BotAction(List.of(
                ACKNOWLEDGE_INTENT_AND_ARE_YOU_A_CUSTOMER_QUERY))
        );
        knownResponses.insert(
            List.of("pay bill", "I like coffee"),
            new BotAction(List.of(
                ACKNOWLEDGE_INTENT_AND_ARE_YOU_A_CUSTOMER_QUERY))
        );
        knownResponses.insert(
            List.of("pay bill", "I like coffee", "I like tea"),
            new BotAction(List.of(
                ACKNOWLEDGE_INTENT_AND_ARE_YOU_A_CUSTOMER_QUERY)));
            knownResponses.insert(
                List.of("pay bill", "I like coffee", "I like tea", "no"),
                new BotAction(List.of(
                    TRANSFER_TO_HUMAN))
        );
        knownResponses.insert(
            List.of("pay bill", "Yes, I'm a customer"),
            new BotAction(List.of(
                TRANSFER_TO_SELF_SERVICE))
        );
        knownResponses.insert(
            List.of("pay bill", "No, I'm not"),
            new BotAction(List.of(
                TRANSFER_TO_HUMAN))
        );

        knownResponses.insert(
            List.of("talk to an operator"),
            new BotAction(List.of(
                HI_I_AM_BOT,
                LET_ME_HELP_YOU,
                ARE_YOU_A_CUSTOMER_QUERY))
        );
    }

    public BotAction search(List<String> messagesSoFar) {
        return knownResponses.search(messagesSoFar);
    }
}
