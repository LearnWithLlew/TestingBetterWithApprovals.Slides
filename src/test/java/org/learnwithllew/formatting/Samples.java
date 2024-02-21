package org.learnwithllew.formatting;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;

public class Samples {

    public void aMethod() {
        String canvas = """
            
            """;
    }

    @Test
    void name() {
        var expected = """
        approved content
        """;
        Approvals.verify(systemUnderTest(), new Options().inline(expected));
    }

    private static String systemUnderTest() {
        return "new content";
    }
}


























