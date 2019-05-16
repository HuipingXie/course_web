package course.dao;

import course.datacontainer.CourseRecordInfo;
import course.model.CourseRecord;
import course.model.Student;
import course.sqlopration.GetMySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseRecordDao{

    /**
     * 新增一条选课记录
     * @param record
     * @return
     */
    public static String addCourseRecord(CourseRecord record){
        String resString;
        //如果用户已经存在此选课记录，则不进行操作
        CourseRecord searchRecord=getCouRecByTimetableIdAndStuId(record.getTimetableID(),record.getStudentID());
        if(searchRecord.getRecordID()!=0){
            return "已选过此门课程";
        }
        String sql=String.format("INSERT INTO `course_record` (`timetable_id`, `student_id`) VALUES ('%s', '%s');",record.getTimetableID(),record.getStudentID());
        Connection con=GetMySQLConnection.getConnection();
        try {
            PreparedStatement pSQL=con.prepareStatement(
                    "INSERT INTO `course_record` (`timetable_id`, `student_id`) VALUES (?,?)"
            );
            pSQL.setInt(1,record.getTimetableID());
            pSQL.setInt(2,record.getStudentID());
            pSQL.executeUpdate();
            resString="success";
        }catch (Exception e){
//            System.out.println(e.getMessage());
            resString=e.getMessage();
        }
        GetMySQLConnection.closeConnection(con);
        return resString;
    }

    /**
     * 通过课表id和学生id获取选课记录,通过方法返回的结果判断用户是否已经选课了
     * @param timetableID 课表id
     * @param studentID 学生id
     * @return
     */
    public static CourseRecord getCouRecByTimetableIdAndStuId(int timetableID,int studentID){
        String sql=String.format("select * from course_record where timetable_id=%s and student_id=%s",timetableID,studentID);
        return getCourseRecordBySQL(sql);
    }

    /**
     * 通过sql语句获取单条选课记录
     * @param sql
     * @return
     */
    public static CourseRecord getCourseRecordBySQL(String sql){
        CourseRecord record=getCourseRecordListBySQL(sql).get(0);
        return  record;
    }

    /**
     * 通过课表id获取当前上此门课的所有人
     * @param timetableID
     * @return
     */
    public static List<Student> getCurrentTimeTableStudents(int timetableID){
        String sql=String.format("select * from course_record where timetable_id=%s",timetableID);
        return getCourseStudentListBySQL(sql);
    }

    /**
     *通过sql语句查询此门课的所有人
     * @param sql
     * @return
     */
    public static List<Student> getCourseStudentListBySQL(String sql){
        List<Student> studentList=new ArrayList<>();
        Connection con= GetMySQLConnection.getConnection();
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                int studentID=rs.getInt("student_id");
                Student student=StudentDao.getStudentByID(studentID);
                studentList.add(student);
            }
        }catch (Exception e){
            e.getMessage();
        }
        GetMySQLConnection.closeConnection(con);
        return studentList;
    }

    /**
     * 查询当前学生的课表id
     * @param studentID
     * @return
     */
    public static List<Integer> getTimeTableIDsByStudentID(int studentID){
        List<Integer> resList=new ArrayList<>();
        String sql=String.format("select * from course_record where student_id=%s",studentID);
        List<CourseRecord> courseRecordList=getCourseRecordListBySQL(sql);
        for(CourseRecord cr:courseRecordList){
            resList.add(cr.getTimetableID());
        }
        return resList;
    }

    /**
     * 通过sql语句获取所有的course_record符合sql的记录列表
     * @param sql
     * @return
     */
    public static List<CourseRecord> getCourseRecordListBySQL(String sql){
        List<CourseRecord> courseRecordList=new ArrayList<>();
        Connection con= GetMySQLConnection.getConnection();
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                CourseRecord record=new CourseRecord();
                record.setTimetableID(rs.getInt("timetable_id"));
                record.setStudentID(rs.getInt("student_id"));
                record.setRecordID(rs.getInt("record_id"));
                record.setScore(rs.getFloat("score"));
                record.setCreateTime(rs.getLong("create_time"));
                record.setUpdateTime(rs.getLong("update_time"));
                courseRecordList.add(record);
            }
        }catch (Exception e){
            e.getMessage();
        }
        GetMySQLConnection.closeConnection(con);
        return courseRecordList;
    }

    /**
     * 老师通过课表id来获取选修当前课的学生的选课记录及学生信息
     * @param timetableID
     * @return
     */
    public static List<CourseRecordInfo> getCourseRecordInfoListByTimetableID(int timetableID){
        String sql=String.format("select * from course_record where timetable_id=%s",timetableID);
        List<CourseRecordInfo> recordInfoList=new ArrayList<>();
        List<CourseRecord> recordList=getCourseRecordListBySQL(sql);
        for(CourseRecord cr:recordList){
            CourseRecordInfo crInfo=new CourseRecordInfo(cr);
            crInfo.setStudent(StudentDao.getStudentByID(cr.getStudentID()));
            recordInfoList.add(crInfo);
        }
        return recordInfoList;
    }

    /**
     * 通过学生id获取所有课程的成绩
     * @param studentID
     * @return
     */
    public static List<CourseRecordInfo> getCourseRecordInfoListByStudentID(int studentID){
        String sql=String.format("select * from course_record where student_id=%s and score!=-1.0",studentID);
        List<CourseRecordInfo> recordInfoList=new ArrayList<>();
        List<CourseRecord> recordList=getCourseRecordListBySQL(sql);
        for(CourseRecord cr:recordList){
            CourseRecordInfo crInfo=new CourseRecordInfo(cr);
            crInfo.setStudent(StudentDao.getStudentByID(cr.getStudentID()));
            crInfo.setTimetableInfo(TimetableDao.getTimetableInfoByTimetableID(cr.getTimetableID()));
            recordInfoList.add(crInfo);
        }
        return recordInfoList;
    }

    /**
     * 通过学生id和课表ID修改学生分数
     * @param studentID
     * @param timetableID
     * @param score
     * @return
     */
    public static boolean updateScore(int studentID,int timetableID,float score){
        Connection con=GetMySQLConnection.getConnection();
        try{
            PreparedStatement pSQL;
            pSQL = con.prepareStatement("update course_record set score =? where timetable_id=? and student_id=?");
            pSQL.setFloat(1,score);
            pSQL.setInt(2,timetableID);
            pSQL.setInt(3,studentID);
            pSQL.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
