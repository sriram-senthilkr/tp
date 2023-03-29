package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.model.ModelManager;

public class InitCommand extends Command {  //set username and course, command: init n/Jiun Yuan c/1
    public static final String COMMAND_WORD = "init";

    public static final String MESSAGE = "Initialization Complete";

    public final String name;

    public final Integer courseCode;

    public InitCommand (String name,  int courseCode ) {
        this.name = name;
        this.courseCode = courseCode;
    }

    @Override
    public CommandResult execute(ModelManager model) throws InvalidCommandException {
        String course = "";
        model.setUserName(name);
        switch(courseCode) {
        case 1: course = "Biomedical Engineering";
            break;
        case 2: course = "Chemical Engineering";
            break;
        case 3: course = "Civil Engineering";
            break;
        case 4: course = "Computer Engineering";
            break;
        case 5: course = "Electrical Engineering";
            break;
        case 6: course = "Environmental Engineering";
            break;
        case 7: course = "Industrial and Systems Engineering";
            break;
        case 8: course = "Mechanical Engineering";
            break;
        default: throw new InvalidCommandException("Enter within the index. Please initialize again");
        }
        model.setUserCourse(course);
        return new CommandResult(MESSAGE, false);
    }
}
