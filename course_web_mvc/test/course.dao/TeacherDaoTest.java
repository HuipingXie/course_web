package course.dao;

import course.model.Teacher;
import org.junit.Test;

public class TeacherDaoTest{


    @Test
    public void getTeacherTest(){
        String teacherCode="2008120101";
        String password="123";
        Teacher teacher=TeacherDao.getTeacher(teacherCode,password);
        System.out.println(teacher.getName()+"\t"+teacher.getCollege());
    }

    @Test
    public void getTeacherByIDTest(){
        int id=1;
        Teacher teacher=TeacherDao.getTeacherByID(id);
        System.out.println(teacher.getName()+"\t"+teacher.getCollege());
    }


}
