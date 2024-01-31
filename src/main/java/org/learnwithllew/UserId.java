package org.learnwithllew;

public class UserId {
    private final Channel channel;
    private final String channelId;

    // Private constructor
    private UserId(Channel channel, String channelId) {
        this.channel = channel;
        this.channelId = channelId;
    }

    // Getters
    public Channel getChannel() {
        return channel;
    }

    public String getChannelId() {
        return channelId;
    }

    // Builder class
    public static class Builder {
        private Channel channel;
        private String channelId;

        public Builder channel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public Builder id(String channelId) {
            this.channelId = channelId;
            return this;
        }

        public UserId build() {
            return new UserId(channel, channelId);
        }
    }
}

