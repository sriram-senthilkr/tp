package seedu.penus.user;

public class User {
    public String name;

    public String course;

    public User() {
        this.name = "";
        this.course = "";
    }

    //overloaded method for initialising prev user
    public User(String name, String course) {
        this.name = name;
        this.course = course;
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

    public String encode() {
        String encoded = String.format("User ### %s ### %s",this.name, this.course);
        return encoded;
    }
}

