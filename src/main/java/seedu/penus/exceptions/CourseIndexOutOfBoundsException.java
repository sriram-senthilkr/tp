package seedu.penus.exceptions;

public class CourseIndexOutOfBoundsException extends Exception{  //work when i replace Exception with PenusException
    private static final String message = "Enter within the index. Please initialize again";
    public CourseIndexOutOfBoundsException(String message) {
        super(message);
    }
}
