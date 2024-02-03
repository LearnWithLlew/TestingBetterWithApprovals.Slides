package org.learnwithllew;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;

public class A2Test {

    @Test
    void moveToApprovals() {
        verifyConversations("hi");
    }

    @Test
    void testJson() {
        verifyConversations("hi", "pay bill");
    }

    @Test
    void botIntroducesItselfAndClarifiesIntent() {
        String expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            """;
        verifyConversation(expected, "hi");
    }

    private void verifyConversations(String... messages) {
        verifyConversation(null, messages);
    }

    private void verifyConversation(String expected, String... messages) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        var storyBoard = StoryBoard.create(bot, output, true, messages);
        Options options = new Options();
        if (expected != null) {
            options = options.inline(expected);
        }
        Approvals.verify(storyBoard, options);
    }
}
