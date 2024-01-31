package org.learnwithllew;

import java.util.Objects;

public class SendPlainMessageCommand  extends Command{
    private final String message;

    public SendPlainMessageCommand(String message) {

        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPlainMessageCommand that = (SendPlainMessageCommand) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
