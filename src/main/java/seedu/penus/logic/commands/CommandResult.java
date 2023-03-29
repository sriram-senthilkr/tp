package seedu.penus.logic.commands;

import java.util.List;

public class CommandResult {
    public final String feedbackToUser;

    public final List<String> feedbackArray;

    public final boolean isArray;

    /**
     * Stores a string feedbackToUser to return the feedback message from an executed command
     * @param feedbackToUser string
     * @param isArray boolean
     */
    public CommandResult(String feedbackToUser, boolean isArray) {
        this.feedbackToUser = feedbackToUser;
        this.feedbackArray = null;
        this.isArray = isArray;
    }

    /**
     * Overloaded constructor for printing arrays
     * Stores a List of string feedbackArray to return the feedback message from an executed command
     * @param feedbackArray string
     * @param isArray boolean
     */
    public CommandResult(List<String> feedbackArray, boolean isArray) {
        this.feedbackToUser = null;
        this.feedbackArray = feedbackArray;
        this.isArray = isArray;
    }
}
