package org.learnwithllew.helper;

import org.learnwithllew.*;

import java.util.List;

public class ExistingCustomerResponses extends HardcodedBotResponses {

    public ExistingCustomerResponses() {
        knownResponses.insert(
            List.of("pay bill"),
            new BotAction(List.of(
                HI_I_AM_BOT,
                LET_ME_HELP_YOU,
                TRANSFER_TO_SELF_SERVICE)));
    }
}
