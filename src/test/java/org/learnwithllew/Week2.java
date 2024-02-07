package org.learnwithllew;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;
import org.learnwithllew.week2.StoryBoard;

// Full printed story -> whitespace -> json
public class Week2 {

    @Test
    void botIntroducesItselfAndClarifiesIntent() {
        verifyConversation("hi");
    }

    @Test
    void test2() {
        verifyConversation("hi", "hi");
    }

    @Test
    void test3() {

        verifyConversation("hi", "hi", "hi");
    }

    @Test
    void test4() {
        verifyConversation("hi", "pay bill");
    }

    @Test
    void test5() {
        verifyConversation("hi", "pay bill", "Yes, I'm a customer");
    }

    @Test
    void testPayBill() {
        verifyConversation("hi", "pay bill");
    }

    private void verifyConversation(String... messages) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        var storyBoard = StoryBoard.create(bot, output, messages);
        Approvals.verify(storyBoard, new Options());
    }
}
