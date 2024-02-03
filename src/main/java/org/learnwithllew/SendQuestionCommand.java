package org.learnwithllew;

public record SendQuestionCommand(String question, String... answers) implements Command {

}
