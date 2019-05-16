package course.dao;

import course.model.Teacher;
import course.sqlopration.GetMySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TeacherDao{

    /**
     * 用户登录判断函数
     * @param teacherCode 教工号
     * @param password 密码
     * @return 返回实例化的教师对象
     */
    public static Teacher getTeacher(String teacherCode,String password){
        String sql=String.format("select * from teacher where teacher_code=%s and password='%s'",teacherCode,password);
        return getTeacherBySQL(sql);
    }

    /**
     * 通过sql语句获取teacher信息
     * @param sql
     * @return
     */
    public static Teacher getTeacherBySQL(String sql){
        Teacher teacher=new Teacher();
        Connection con= GetMySQLConnection.getConnection();
        try {
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                teacher.setTeacherID(rs.getInt("teacher_id"));
                teacher.setName(rs.getString("name"));
                teacher.setCollege(rs.getString("college"));
                teacher.setTeacherCode(rs.getInt("teacher_code"));
                teacher.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        GetMySQLConnection.closeConnection(con);
        return teacher;
    }

    /**
     * 通过teacher_id获取teacher的类
     * @param teacherID
     * @return
     */
    public static Teacher getTeacherByID(int teacherID){
        String sql=String.format("select * from teacher where teacher_id=%s",teacherID);
        return getTeacherBySQL(sql);
    }



}
