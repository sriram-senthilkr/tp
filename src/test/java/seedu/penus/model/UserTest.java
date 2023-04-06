package seedu.penus.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    static final String EMPTY_STRING = "";
    public User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }
    @Test
    public void testDefaultConstructor() {
        assertEquals(EMPTY_STRING, user.getName());
        assertEquals(EMPTY_STRING, user.getCourse());
    }

    @Test
    public void testOverloadedConstructor() {
        User user = new User("John Doe", "Computer Engineering");
        String name = "John Doe";
        String course = "Computer Engineering";
        assertEquals(name, user.getName());
        assertEquals(course, user.getCourse());
    }

    @Test
    public void testSetName() {
        String name = "Jane Doe";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testSetCourse() {
        String course = "Computer Engineering";
        user.setCourse(course);
        assertEquals(course, user.getCourse());
    }

    @Test
    public void testEncode() {
        String name = "John Doe";
        String course = "Computer Engineering";
        user = new User(name, course);
        String expected = String.format("User ### %s ### %s", name, course);
        assertEquals(expected, user.encode());
    }
}
