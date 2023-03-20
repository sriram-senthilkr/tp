package seedu.penus.user;

public class User {
    public String name;

    public String course;

    public User() {
        this.name = "";
        this.course = "";
    }
    public void setName(String inputName) {
        this.name = inputName;
    }

    public void setCourse(String inputCourse){
        this.course = inputCourse;
    }

    public String getName(){
        return this.name;
    }

    public String getCourse(){
        return this.course;
    }
}

