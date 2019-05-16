package course.dao;

import course.model.Course;
import course.sqlopration.GetMySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CourseDao{

    /**
     * 通过sql语句获取course对象
     * @param sql
     * @return
     */
    public static Course getCourseBySQL(String sql){
        Course course=new Course();
        Connection con= GetMySQLConnection.getConnection();
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                course.setCourseID(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setCourseDesc(rs.getString("course_desc"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        GetMySQLConnection.closeConnection(con);
        return course;
    }

    /**
     * 通过courserID获取Course对象
     * @param courseID
     * @return
     */
    public static Course getCourseByID(int courseID){
        String sql=String.format("select * from course where course_id=%s",courseID);
        return getCourseBySQL(sql);
    }



}
