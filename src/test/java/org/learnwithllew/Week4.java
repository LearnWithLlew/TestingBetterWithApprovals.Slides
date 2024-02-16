package org.learnwithllew;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;
import org.learnwithllew.week4.StoryBoard;
import org.learnwithllew.week4.Conversations;

// everywhere -> single instance -> some can’t convert
public class Week4 {

    @Test
    void test1() {
        var expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            """;
        verifyConversations(expected, "hi");
    }

    @Test
    void test4() {
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
        verifyConversations(expected, "hi", "pay bill");
    }

    @Test
    void test10() {
        var expected = """
            [Customer]: pay bill
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: I like coffee
            [     Bot]: I'd be happy to help you with this, But first, I need to know: are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: I like tea
            [     Bot]: I'd be happy to help you with this, But first, I need to know: are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: no
            [     Bot]: transfers to 'operator'
            """;
        verifyConversations(expected, "pay bill", "I like coffee", "I like tea", "no");
    }

    @Test
    void test5() {
        var expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            [Customer]: pay bill
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: Yes, I'm a customer
            [     Bot]: transfers to 'self_service'
            """;
        verifyConversations(expected, "hi", "pay bill", "Yes, I'm a customer");
    }

    @Test
    void test2() {
        var expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            [Customer]: hi
            [     Bot]: Hmmm, tell me a little more so I can help you.
            What would you like to do today?
            """;
        verifyConversations(expected, "hi", "hi");
    }

    @Test
    void test3() {
        var expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            [Customer]: hi
            [     Bot]: Hmmm, tell me a little more so I can help you.
            What would you like to do today?
            [Customer]: hi
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            """;
        verifyConversations(expected, "hi", "hi", "hi");
    }



    @Test
    void test6() {
        var expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            [Customer]: pay bill
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: no
            [     Bot]: transfers to 'operator'
            """;
        verifyConversations(expected, "hi", "pay bill", "no");
    }

    @Test
    void test7() {
        var expected = """
            [Customer]: hi
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: What would you like to do today?
            [Customer]: talk to an operator
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: Yes, I'm a customer
            [     Bot]: transfers to 'operator'
            """;
        verifyConversations(expected, "hi", "talk to an operator", "Yes, I'm a customer");
    }

    @Test
    void test8() {
        var expected = """
            [Customer]: pay bill
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            """;
        verifyConversations(expected, "pay bill");
    }

    @Test
    void test9() {
        var expected = """
            [Customer]: pay bill
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: I like swimming
            [     Bot]: I'd be happy to help you with this, But first, I need to know: are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            """;
        verifyConversations(expected, "pay bill", "I like swimming");
    }

    @Test
    void test11() {
        var expected = """
            [Customer]: talk to an operator
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            """;
        verifyConversations(expected, "talk to an operator");
    }

    @Test
    void test12() {
        var expected = """
            [Customer]: oh hi there, how are you doing
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            """;
        verifyConversations(expected, "oh hi there, how are you doing");
    }

    @Test
    void test13() {
        var expected = """
            [Customer]: walk my dog
            [     Bot]: Hi there! I'm your virtual assistant.
            [     Bot]: Let me try to help you.
            [     Bot]: Are you a customer?
            [        ]:   1) Yes, I'm a customer
            [        ]:   2) No, I'm not
            [Customer]: Yes, I'm a customer
            [     Bot]: transfers to 'operator'
            """;
        verifyConversations(expected, "walk my dog", "Yes, I'm a customer");
    }

    private void verifyConversations(String expected, String... messages) {
        Conversations conversations = new Conversations(messages);
        Approvals.verify(playConversation(conversations), new Options().inline(expected));
    }

    private static String playConversation(Conversations conversations) {
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        return StoryBoard.create(bot, output, conversations);
    }
}
