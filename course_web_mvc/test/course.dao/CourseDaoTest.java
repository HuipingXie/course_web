package course.dao;

import course.model.Course;
import org.junit.Test;

public class CourseDaoTest {

    @Test
    public void getCourseByIDTest(){
        int id=1;
        Course course=CourseDao.getCourseByID(id);
        System.out.println(course.getCourseName()+"\t"+course.getCourseDesc());
    }


}
