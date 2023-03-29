package seedu.penus.logic.commands;

public class CommandResult {
    public final String feedbackToUser;

    /**
     * Stores a string feedbackToUser to return the feedback message from an executed command
     * @param feedbackToUser string
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
}
