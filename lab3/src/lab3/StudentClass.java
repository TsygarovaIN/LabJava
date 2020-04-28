package lab3;

public class StudentClass {
    public String subjectName;
    public int labsCount;

    StudentClass(String subjectName, int labsCount) {
        this.subjectName = subjectName;
        this.labsCount = labsCount;
    }

    @Override
    public String toString() {
        String s = "Student in " + subjectName + " has " + labsCount + "labs";
        return s;
    }
}
