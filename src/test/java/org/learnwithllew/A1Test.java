package org.learnwithllew;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class A1Test {

    @Test
    void botIntroducesItselfAndClarifiesIntent() {
        // given
        EventNotification message = TestUtils.eventNotification()
            .userId(new UserId.Builder().channel(Channel.TEXT).id(UUID.randomUUID().toString()).build())
            .properties(List.of(
                new Property("accountId", "1234567"),
                new Property("code", "411")))
            .events(List.of(new MessageEvent(System.currentTimeMillis(), "0", "hi"))).build();
        BotOutput output = new BotOutput();
        Bot bot = new Bot(output);
        // when
        bot.handle(message);
        // then
        BotAction botActions = output.read(message.getConversationId());
        assertThat(botActions.commands())
            .containsExactly(
                new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
                new SendPlainMessageCommand("What would you like to do today?")
            );
    }


}
