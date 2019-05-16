package course.sqlopration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetMySQLConnection {
    private static  String driverName = "com.mysql.jdbc.Driver";
    private static String dbURL = "jdbc:mysql://localhost:3306/course?useSSL=false";
    private static String userName = "root";
    private static String userPwd = "wuyingyao";

    public static Connection getConnection(){
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //关闭数据库连接
    public static void closeConnection(Connection con){
        try {
            con.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
