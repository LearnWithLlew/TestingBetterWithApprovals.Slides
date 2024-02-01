package org.learnwithllew;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

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

    private void verifyConversation(String expected, String textMessages) {
        // given
        EventNotification message = TestUtils.eventNotification()
            .userId(new UserId.Builder().channel(Channel.TEXT).id(UUID.randomUUID().toString()).build())
            .properties(List.of(
                new Property("accountId", "1234567"),
                new Property("code", "411")))
            .events(List.of(new MessageEvent(System.currentTimeMillis(), "0", textMessages))).build();
        Bot bot = new Bot();
        BotOutput output = new BotOutput();
        // when
        bot.handle(message);
        // then
        BotAction botActions = output.read(message.getConversationId());
        var actual = botActions.getCommandLog1();
        Approvals.verify(actual, new Options().inline(expected));

    }
}
