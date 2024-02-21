package org.learnwithllew.week3;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DelayedClipboardReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;
import org.learnwithllew.Bot;
import org.learnwithllew.BotOutput;
import org.learnwithllew.week3.StoryBoard;

// Pretty printed json -> inline -> everywhere
public class Week3 {

    @Test
    void botClarifiesIntentOnGreeting() {
        verifyConversation("hi");
    }

    @Test
    void repeatedGreetingGetsAnotherPrompt() {
        verifyConversation("hi", "hi");
    }

    @Test
    void firstMessageIsNotGreeting() {
        verifyConversation( "pay bill");
    }

    @Test
    void botOffersSelfServiceOnSupportedIntents() {
        verifyConversation("hi", "pay bill", "Yes, I'm a customer");
    }

    @Test
    void customerEscalatesToAgentThenBotRoutesToHumanAgent() {
        verifyConversation("hi", "talk to an operator", "Yes, I'm a customer");
    }

    @Test
    void customerStartsWithEscalatingToAgent() {
        verifyConversation("talk to an operator");
    }

    private void verifyConversation(String... messages) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        var storyBoard = StoryBoard.create(bot, output, messages);
        Approvals.verify(storyBoard);
    }
}
