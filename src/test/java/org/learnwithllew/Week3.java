package org.learnwithllew;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;
import org.learnwithllew.week3.StoryBoard;

// Pretty printed json -> inline -> everywhere
public class Week3 {

    @Test
    void testPrettyButtons() {
        verifyConversations("hi", "pay bill");
    }

    @Test
    void testPrettyButtonsInline() {
        var expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            [Customer]: pay bill
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            """;
        verifyConversation(expected, "hi", "pay bill");
    }

    private void verifyConversations(String... messages) {
        verifyConversation(null, messages);
    }

    private void verifyConversation(String expected, String... messages) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        var storyBoard = StoryBoard.create(bot, output, messages);
        Options options = new Options();
        if (expected != null) {
            options = options.inline(expected);
        }
        Approvals.verify(storyBoard, options);
    }
}
