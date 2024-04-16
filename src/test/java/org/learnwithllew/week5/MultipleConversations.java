package org.learnwithllew.week5;

import java.util.ArrayList;
import java.util.Arrays;

public class MultipleConversations extends ArrayList<Conversation> {

    public MultipleConversations(Conversation... conversations) {
        this.addAll(Arrays.asList(conversations));
    }
}
