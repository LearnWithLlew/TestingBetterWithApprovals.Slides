package org.learnwithllew;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
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
    void botOffersSelfServiceOnSupportedIntents() {
        verifyConversation("hi", "pay bill", "Yes, I'm a customer");
    }

    private void verifyConversation(String... messages) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        var storyBoard = StoryBoard.create(bot, output, messages);
        Approvals.verify(storyBoard);
    }
}
