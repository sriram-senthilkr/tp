package seedu.penus.logic.commands;

public class CommandResult {
    public final String feedbackToUser;

    /**
     * Stores a string feedbackToUser to return the feedback message from an executed command
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
}
