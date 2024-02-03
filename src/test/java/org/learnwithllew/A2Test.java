package org.learnwithllew;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;

public class A2Test {

    @Test
    void moveToApprovals() {
        verifyConversation("hi");
    }

    @Test
    void testJson() {
        verifyConversation("hi", "pay bill");
    }

    private void verifyConversation(String... messages) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        var storyBoard = StoryBoard.create(bot, output, true, messages);
        Approvals.verify(storyBoard, new Options());
    }
}
