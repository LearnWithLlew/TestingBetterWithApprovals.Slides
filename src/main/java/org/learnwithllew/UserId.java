package org.learnwithllew;

public class UserId {
    private final String id;
    private final Channel channel;

    private UserId(String id, Channel channel) {
        this.channel = channel;
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getId() {
        return id;
    }

    public static class Builder {
        private String id;
        private Channel channel;

        public Builder id(String channelId) {
            this.id = channelId;
            return this;
        }

        public Builder channel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public UserId build() {
            return new UserId(id, channel);
        }
    }
}

