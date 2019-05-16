package course.dao;

import course.datacontainer.TimetableInfo;
import course.model.Teacher;
import course.model.Timetable;
import course.sqlopration.GetMySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimetableDao{

    /**
     * 获取所有的课表信息
     * @return
     */
    public static List<TimetableInfo> getAllTimetableInfo(){
        String sql="select * from timetable";
        return getTimeTableInfoBySQL(sql);
    }

    /**
     * 通过timetableID获取timetableInfo的信息
     * @param timetableID
     * @return
     */
    public static TimetableInfo getTimetableInfoByTimetableID(int timetableID){
        String sql=String.format("select * from timetable where timetable_id=%s",timetableID);
        List<TimetableInfo> infoList=getTimeTableInfoBySQL(sql);
        if(infoList.size()>0){
            return infoList.get(0);
        }else{
            return new TimetableInfo();
        }
    }

    /**
     *
     * @param courseID
     * @return
     */
    public static List<TimetableInfo> getTimetableInfoByCourseID(int courseID){
        String sql=String.format("select * from timetable where course_id=%s",courseID);
        return getTimeTableInfoBySQL(sql);
    }

    /**
     * 学生获取自己已选的课程,思路是先查询学生选的课程的课表id,再查询课表信息
     * @param studentID
     * @return
     */
    public static List<TimetableInfo> getTimetableInfoByStudentID(int studentID){
        StringBuffer selectedTimetableIdStr=new StringBuffer("(");
        List<Integer> selectedTimetableIdList=CourseRecordDao.getTimeTableIDsByStudentID(studentID);
        int lSize=selectedTimetableIdList.size();
        for (int i = 0; i < lSize-1; i++) {
            selectedTimetableIdStr.append(selectedTimetableIdList.get(i)+",");
        }
        selectedTimetableIdStr.append(selectedTimetableIdList.get(lSize-1)+")");
        String sql=String.format("select * from timetable where timetable_id in %s",selectedTimetableIdStr.toString());
        return  getTimeTableInfoBySQL(sql);
    }

    /**
     * 通过sql获取timetable的列表
     * @param sql
     * @return
     */
    public static List<TimetableInfo> getTimeTableInfoBySQL(String sql){
        List<TimetableInfo> timetableInfoList=new ArrayList<>();
        Connection con= GetMySQLConnection.getConnection();
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                TimetableInfo timetableInfo=new TimetableInfo();
                int courseID=rs.getInt("course_id");
                int teacherID=rs.getInt("teacher_id");

                timetableInfo.setTimetableID(rs.getInt("timetable_id"));
                timetableInfo.setCourseID(courseID);
                timetableInfo.setTeacherID(teacherID);
                timetableInfo.setClassroom(rs.getString("classroom"));
                timetableInfo.setCourseOrder(rs.getInt("course_order"));
                timetableInfo.setCourseDay(rs.getInt("course_day"));
                timetableInfo.setCourseTime(rs.getString("course_time"));

                timetableInfo.setCourse(CourseDao.getCourseByID(courseID));
                timetableInfo.setTeacher(TeacherDao.getTeacherByID(teacherID));
                timetableInfoList.add(timetableInfo);
            }
        }catch (Exception e){
            e.getMessage();
        }
        GetMySQLConnection.closeConnection(con);
        return timetableInfoList;
    }


    /**
     * 通过teacherID获取timetable（课表）信息，即获取当前teacher的所有教授课程
     * @param teacherID
     * @return
     */
    public static List<TimetableInfo> getTimetableInfoByTeacherID(int teacherID){
        String sql=String.format("select * from timetable where teacher_id=%s",teacherID);
        return getTimeTableInfoBySQL(sql);
    }




}
