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
            .userId(UserId.builder().channel(Channel.TEXT).channelId(UUID.randomUUID().toString()).build())
            .properties(List.of(
                new Property("accountId", "1234567"),
                new Property("code", "411")))
            .events(List.of(new MessageEvent(System.currentTimeMillis(), "0", "hi"))).build();
        BotNotificationHandler notificationHandler = new BotNotificationHandler();
        BotOutboundChannel botOutboundChannel = new BotOutboundChannel();
        // when
        notificationHandler.handle(message);
        // then
        BotAction botActions = botOutboundChannel.read(message.getConversationId());
        assertThat(botActions.getCommands())
            .containsExactly(
                new SendPlainMessageCommand("Hi there! I'm your virtual assistant."),
                new SendPlainMessageCommand("What would you like to do today?")
            );
    }


}
