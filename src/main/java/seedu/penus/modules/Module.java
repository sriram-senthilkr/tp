package seedu.penus.modules;

public class Module {
    protected String moduleCode;
    protected Integer year;
    protected Integer semester;
    protected String grade;
    protected boolean isTaken;

    public Module(String moduleCode, Integer year, Integer semester) {
        this.moduleCode = moduleCode;
        this.year = year;
        this.semester = semester;
        this.isTaken = false;
    }

    //overloaded method for taken modules
    public Module(String moduleCode, Integer year, Integer semester, String grade) {
        this.moduleCode = moduleCode;
        this.year = year;
        this.semester = semester;
        this.grade = grade;
        this.isTaken = true;
    }

    public String getCode() {
        return this.moduleCode;
    }

    public void markTaken(String grade) {
        this.isTaken = true;
        this.grade = grade;
    }

    public void markUntaken() {
        this.isTaken = false;
        this.grade = "";   
    }

    public String getStatus() {
        return (isTaken ? "Taken" : "Plan");
    }

    public String getGrade() {
        return (isTaken ? grade : "");
    }

    //TODO: change format accordingly
    public String toString() {
        return this.getStatus() + " " + this.moduleCode + " " + this.year + " " + this.semester + " " + getGrade();    
    }
}
