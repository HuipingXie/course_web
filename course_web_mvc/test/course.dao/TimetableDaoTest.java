package course.dao;

import course.datacontainer.TimetableInfo;
import org.junit.Test;

import java.util.List;

public class TimetableDaoTest {

    /**
     * 测试获取所有的课表
     */
    @Test
    public void getAllTimetableTest(){
        List<TimetableInfo> timetableInfoList=TimetableDao.getAllTimetableInfo();
        for (TimetableInfo ti: timetableInfoList){
            System.out.println(ti.getCourse().getCourseName()+"\t"+ti.getClassroom()+"\t"+ti.getTeacher().getName());
        }
    }

    @Test
    public void getStuTimetableInfo(){
        List<TimetableInfo> timetableInfoList=TimetableDao.getTimetableInfoByStudentID(1);
        for (TimetableInfo ti: timetableInfoList){
            System.out.println(ti.getCourse().getCourseName()+"\t"+ti.getClassroom()+"\t"+ti.getTeacher().getName());
        }
    }

    @Test
    public void getSingleTimetable(){
        TimetableInfo ti=TimetableDao.getTimetableInfoByTimetableID(1);
        System.out.println(ti.getCourse().getCourseName()+"\t"+ti.getClassroom()+"\t"+ti.getTeacher().getName());
    }

}
