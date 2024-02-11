package org.learnwithllew;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.approvaltests.Approvals.verify;
import static org.assertj.core.api.Assertions.assertThat;

// Lada’s tests -> approval test (of the list) -> better printing
public class Week1 {

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
        bot.receive(message);
        // then
        BotAction botActions = output.read(message.getConversationId());
        assertThat(botActions.commands())
            .containsExactly(
                new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
                new SendPlainMessageCommand("What would you like to do today?")
            );
    }

    @Test
    void repeatedGreeting() {
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
        bot.receive(message);
        // then
        BotAction response = output.read(message.getConversationId());
        assertThat(response.commands())
            .containsExactly(
                new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
                new SendPlainMessageCommand("What would you like to do today?"));
        bot.receive(TestUtils.messageFromCustomer("2", "hi").build());
        // then
        BotAction secondResponse = output.read(message.getConversationId());
        assertThat(secondResponse.commands())
            .containsExactly(
                new SendPlainMessageCommand("Hmmm, tell me a little more so I can help you.\n" +
                    "What would you like to do today?")
            );
    }
}
