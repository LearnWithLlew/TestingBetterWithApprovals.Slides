package org.learnwithllew.helper;

import org.learnwithllew.BotAction;
import org.learnwithllew.SendPlainMessageCommand;
import org.learnwithllew.SendQuestionCommand;
import org.learnwithllew.TransferConversationCommand;

import java.util.List;

public class ProspectiveCustomerResponses extends HardcodedBotResponses {

    public ProspectiveCustomerResponses() {
        knownResponses.insert(
            List.of("hi"),
            new BotAction(List.of(
                HI_I_AM_BOT,
                WHAT_WOULD_YOU_LIKE_TO_DO)));
        knownResponses.insert(
            List.of("hi", "pay bill"),
            new BotAction(List.of(
                TRANSFER_TO_HUMAN)));
        knownResponses.insert(
            List.of("pay bill"),
            new BotAction(List.of(
                LET_ME_HELP_YOU,
                TRANSFER_TO_HUMAN))
        );
    }

}
