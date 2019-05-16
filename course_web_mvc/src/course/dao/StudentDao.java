package course.dao;
import course.model.Course;
import course.model.Student;
import course.sqlopration.GetMySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDao{


    /**
     * 通过学生学号，密码获取学生信息
     * @param code
     * @param password
     * @return
     */
    public static Student getStudent(String code,String password){
        String sql=String.format("select * from student where student_code=%s and password='%s'",code,password);
        return getStudentBySQL(sql);
    }

    /**
     * 通过学生id来获取学生信息
     * @param studentID
     * @return
     */
    public static Student getStudentByID(int studentID){
        String sql=String.format("select * from student where student_id=%s",studentID);
        return getStudentBySQL(sql);
    }

    /**
     * 通过sql语句查询student信息
     * @param sql
     * @return
     */
    public static Student getStudentBySQL(String sql){
        Student student=new Student();
        Connection con= GetMySQLConnection.getConnection();
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                student.setStudentID(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setCollege(rs.getString("college"));
                student.setStudentCode(rs.getInt("student_code"));
                student.setClassName(rs.getString("class_name"));
                student.setMajor(rs.getString("major"));
                student.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        GetMySQLConnection.closeConnection(con);
        return student;
    }



}
