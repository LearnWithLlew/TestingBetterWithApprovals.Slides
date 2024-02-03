package org.learnwithllew;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;

public class A2Test {

    @Test
    void botIntroducesItselfAndClarifiesIntent() {
        String expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            """;
        verifyConversation(expected, "hi");
    }

    private void verifyConversation(String expected, String... messages) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        var storyBoard = StoryBoard.create(bot, output, messages);
        Approvals.verify(storyBoard, new Options().inline(expected));
    }
}
