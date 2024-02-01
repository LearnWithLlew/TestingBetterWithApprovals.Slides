package org.learnwithllew;

public class MessageEvent {

    private final long time;
    private final String number;
    private final String message;

    public MessageEvent(long time, String number, String message) {
        this.time = time;
        this.number = number;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
