package course.datacontainer;

import course.model.CourseRecord;
import course.model.Student;
import course.model.Timetable;

public class CourseRecordInfo{
    private int recordID;
    private int timetableID;
    private int studentID;
    private float score;
    private long createTime;
    private long updateTime;
    private Student student;
    private Timetable timetable;
    private TimetableInfo timetableInfo;

    public CourseRecordInfo(){

    }
    public CourseRecordInfo(CourseRecord courseRecord){
        this.recordID=courseRecord.getRecordID();
        this.timetableID=courseRecord.getTimetableID();
        this.studentID=courseRecord.getStudentID();
        this.score=courseRecord.getScore();
        this.createTime=courseRecord.getCreateTime();
        this.updateTime=courseRecord.getUpdateTime();
    }

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(int timetableID) {
        this.timetableID = timetableID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public TimetableInfo getTimetableInfo() {
        return timetableInfo;
    }

    public void setTimetableInfo(TimetableInfo timetableInfo) {
        this.timetableInfo = timetableInfo;
    }
}
