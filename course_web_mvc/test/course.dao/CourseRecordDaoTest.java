package course.dao;

import course.datacontainer.CourseRecordInfo;
import course.model.CourseRecord;
import org.junit.Test;

import java.util.List;

public class CourseRecordDaoTest{

    @Test
    public void addRecordTest(){
        CourseRecord record=new CourseRecord();
        record.setStudentID(1);
        record.setTimetableID(2);
        String res=CourseRecordDao.addCourseRecord(record);
        System.out.println(res);
    }

    @Test
    public void getRecordTest(){
        int studentID=1;
        List<CourseRecordInfo> infoList=CourseRecordDao.getCourseRecordInfoListByStudentID(studentID);
        for(CourseRecordInfo info:infoList){
            System.out.println(info.getStudent().getName()+"\t"+
                    info.getTimetableInfo().getCourse().getCourseName()+"\t"+
                    info.getTimetableInfo().getTeacher().getName()
                    );
        }

    }


}
