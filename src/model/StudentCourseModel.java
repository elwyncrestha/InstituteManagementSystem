package model;

public class StudentCourseModel {
    private int studentId;
    private int courseId;
    private int studentCourseId;
    private String studentName;
    private String courseName;

    public StudentCourseModel() {
    }

    public StudentCourseModel(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public StudentCourseModel(int studentId, int courseId, int studentCourseId, String studentName, String courseName) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentCourseId = studentCourseId;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
