package course.dao;

import course.model.Student;
import org.junit.Test;

public class StudentDaoTest {

    @Test
    public void getStudentTest(){
        String studentCode="2017111907";
        String password="123";
        Student student= StudentDao.getStudent(studentCode,password);
        System.out.println(student.getClassName()+"\t"+student.getName());
    }

    @Test
    public void getStudentByIDTest(){
        int id=1;
        Student student= StudentDao.getStudentByID(id);
        System.out.println(student.getClassName()+"\t"+student.getName());
    }


}
