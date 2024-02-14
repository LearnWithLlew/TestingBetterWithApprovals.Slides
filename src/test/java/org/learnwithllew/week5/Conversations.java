package org.learnwithllew.week5;

import com.spun.util.ArrayUtils;
import org.lambda.query.Queryable;
import org.learnwithllew.CustomerType;

import java.util.Arrays;

public class Conversations {

    public String[] messages;

    public CustomerType customerType = CustomerType.UNKNOWN;

    public static Conversations conversation(String... messages) {
        return new Conversations(messages);
    }

    public Conversations(String... conversations) {
        messages = conversations;
    }

    public String printMessages() {
        return  "[" + String.join(", ", this.messages) + "]";
    }
}
