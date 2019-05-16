package course.controller;

import com.sun.org.glassfish.gmbal.ParameterNames;
import course.dao.CourseRecordDao;
import course.dao.StudentDao;
import course.dao.TimetableDao;
import course.datacontainer.CourseRecordInfo;
import course.datacontainer.TimetableInfo;
import course.interceptor.TeacherRequireLogin;
import course.model.CourseRecord;
import course.model.Student;
import course.model.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    /**
     * teacher用户主页
     * @return
     */
    @RequestMapping("index")
    @TeacherRequireLogin
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        Teacher teacher=(Teacher)request.getSession().getAttribute("teacher");
        model.addAttribute("teacher",teacher);
        return "teacher/index";
    }

    /**
     * 教师目前教授的课程列表
     * @return
     */
    @RequestMapping("teachingCourse")
    @TeacherRequireLogin
    public String teachingCourse(HttpServletRequest request,HttpServletResponse response,Model model){
        int teacherID=(int)request.getSession().getAttribute("teacher_id");
        List<TimetableInfo> timetableInfoList= TimetableDao.getTimetableInfoByTeacherID(teacherID);
        model.addAttribute("timetableListSize",timetableInfoList.size());
        model.addAttribute("timetableInfoList",timetableInfoList);
        return "teacher/teachingCourse";
    }


    /**
     * 课程选修情况
     * @return
     */
    @RequestMapping("electiveSituation")
    @TeacherRequireLogin
    public String electiveSituation(@RequestParam("timetable_id") int timetableID,Model model){
        TimetableInfo timetableInfo=TimetableDao.getTimetableInfoByTimetableID(timetableID);
        model.addAttribute("timetableInfo",timetableInfo);
        List<Student> studentList= CourseRecordDao.getCurrentTimeTableStudents(timetableID);
        model.addAttribute("studentList",studentList);
        return "teacher/electiveSituation";
    }

    /**
     * 获取课程的详细信息，诸如上课时间、地点、老师等
     * @return
     */
    @RequestMapping(value = "courseInfo",method = RequestMethod.GET)
    @TeacherRequireLogin
    public String courseInfo(@RequestParam("timetable_id") int timetableID,Model model){
        TimetableInfo timetableInfo=TimetableDao.getTimetableInfoByTimetableID(timetableID);
        model.addAttribute("timetableInfo",timetableInfo);
        return "teacher/courseInfo";
    }


    /**
     * 老师录入成绩页面
     * @return
     */
    @RequestMapping("inputScore")
    @TeacherRequireLogin
    public String inputScore(@RequestParam("timetable_id") int timetableID,Model model){
        TimetableInfo timetableInfo=TimetableDao.getTimetableInfoByTimetableID(timetableID);
        model.addAttribute("timetableInfo",timetableInfo);
        List<CourseRecordInfo> recordInfoList= CourseRecordDao.getCourseRecordInfoListByTimetableID(timetableID);
        model.addAttribute("recordInfoList",recordInfoList);

        return "teacher/inputScore";
    }

    @RequestMapping("studentInfo")
    @TeacherRequireLogin
    public String studentInfo(@RequestParam("student_id") int studentID,Model model){
        model.addAttribute("student", StudentDao.getStudentByID(studentID));
        return "teacher/studentInfo";
    }

    /**
     * 以下是处理表单提交数据的操作
     */

    @RequestMapping(value = "updateScore",method = RequestMethod.POST)
    @ResponseBody
    @TeacherRequireLogin
    public Map updateScore(@RequestParam("student_id") int studentID,@RequestParam("timetable_id") int timetableID,@RequestParam("score") float score){
        Map<String,Object> resMap=new ConcurrentHashMap<>();
        boolean isSuccess=CourseRecordDao.updateScore(studentID,timetableID,score);
        resMap.put("res",isSuccess);
        return resMap;
    }


}
