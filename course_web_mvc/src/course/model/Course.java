package course.model;

/**
 * 课程类
 */
public class Course {
    private int courseID;
    private String courseName;
    private String courseDesc;


    //无参构造
    public Course(){

    }
    //含id构造
    public Course(int courseID){

    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

}
