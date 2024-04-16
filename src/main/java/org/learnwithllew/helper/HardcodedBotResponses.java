package org.learnwithllew.helper;

import org.learnwithllew.BotAction;
import org.learnwithllew.SendPlainMessageCommand;
import org.learnwithllew.SendQuestionCommand;
import org.learnwithllew.TransferConversationCommand;

import java.util.List;

public class HardcodedBotResponses {

    public static final SendPlainMessageCommand HI_I_AM_BOT = new SendPlainMessageCommand("Hi there! I'm your virtual assistant.");
    public static final SendPlainMessageCommand LET_ME_HELP_YOU = new SendPlainMessageCommand("Let me try to help you.");
    public static final SendQuestionCommand ARE_YOU_A_CUSTOMER_QUERY = new SendQuestionCommand("Are you a customer?", "Yes, I'm a customer", "No, I'm not");
    public static final SendQuestionCommand ACKNOWLEDGE_INTENT_AND_ARE_YOU_A_CUSTOMER_QUERY = new SendQuestionCommand(
        "I'd be happy to help you with this, But first, I need to know: are you a customer?",
        "Yes, I'm a customer", "No, I'm not");
    public static final SendPlainMessageCommand WHAT_WOULD_YOU_LIKE_TO_DO = new SendPlainMessageCommand("What would you like to do today?");
    public static final TransferConversationCommand TRANSFER_TO_HUMAN = new TransferConversationCommand("operator");
    public static final TransferConversationCommand TRANSFER_TO_SELF_SERVICE = new TransferConversationCommand("self_service");
    protected Trie knownResponses = new Trie();

    public BotAction search(List<String> messagesSoFar) {
        return knownResponses.search(messagesSoFar);
    }
}
