package org.learnwithllew;

import org.junit.jupiter.api.Test;

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



    private void verifyConversation(String expected, String message) {

    }
}
