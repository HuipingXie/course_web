package course.controller;

import course.dao.CourseDao;
import course.dao.CourseRecordDao;
import course.dao.TimetableDao;
import course.datacontainer.CourseRecordInfo;
import course.datacontainer.TimetableInfo;
import course.interceptor.StudentRequireLogin;
import course.model.CourseRecord;
import course.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("student")
public class StudentController {

    @RequestMapping("index")
    @StudentRequireLogin
    public String index(HttpServletRequest request, HttpServletResponse response,Model model){
        Student student=(Student)request.getSession().getAttribute("student");
        model.addAttribute("student",student);
        model.addAttribute("username",student.getName());
        return "student/index";
    }

    /**
     * 选课界面，返回所有的课程列表
     * @return
     */
    @RequestMapping("selectCourses")
    @StudentRequireLogin
    public String chooseCourse(HttpServletRequest request,HttpServletResponse response,Model model){
        Student student=(Student)request.getSession().getAttribute("student");
        List<TimetableInfo> timetableInfoList=TimetableDao.getAllTimetableInfo();
        model.addAttribute("timetableListSize",timetableInfoList.size());
        model.addAttribute("timetableInfoList",timetableInfoList);
        model.addAttribute("username",student.getName());
        return "student/selectCourses";
    }

    /**
     *已选的课程
     * @return
     */
    @RequestMapping("selectedCourses")
    @StudentRequireLogin
    public String selectedCourses(HttpServletRequest request,HttpServletResponse response,Model model){
        int studentID=(int)request.getSession().getAttribute("student_id");
        List<TimetableInfo> timetableInfoList=TimetableDao.getTimetableInfoByStudentID(studentID);
        model.addAttribute("timetableListSize",timetableInfoList.size());
        model.addAttribute("timetableInfoList",timetableInfoList);
        String username=request.getSession().getAttribute("user").toString();
        model.addAttribute("username",username);
        return "student/selectedCourses";
    }

    /**
     * 课程信息页面，需要传入课表id，即需要找到教师和课程信息及课表信息
     * @return
     */
    @RequestMapping(value = "/courseInfo",method = RequestMethod.GET)
    @StudentRequireLogin
    public String courserInfo(HttpServletRequest request,@RequestParam("timetable_id") int timetableID, Model model){

        model.addAttribute("timetableInfo",TimetableDao.getTimetableInfoByTimetableID(timetableID));
        String username=request.getSession().getAttribute("user").toString();
        model.addAttribute("username",username);
        return "student/courseInfo";
    }

    /**
     * 获取成绩页面
     * @return
     */
    @RequestMapping("getScore")
    @StudentRequireLogin
    public String getScore(HttpServletRequest request,HttpServletResponse response,Model model){
        int studentID=(int)request.getSession().getAttribute("student_id");
        List<CourseRecordInfo> recordInfoList=CourseRecordDao.getCourseRecordInfoListByStudentID(studentID);
        model.addAttribute("recordInfoList",recordInfoList);
        String username=request.getSession().getAttribute("user").toString();
        model.addAttribute("username",username);
        return "student/getScore";
    }


    /*
     * 以下是一些表单操作
     */

    /**
     * 异步处理选课的函数
     * @param request
     * @param timetableID
     * @return
     */
    @RequestMapping(path = "doSelectCourse",method = RequestMethod.POST)
    @ResponseBody
    @StudentRequireLogin
    public Map selectCourses(HttpServletRequest request,@RequestParam("timetable_id") int timetableID){
        Map<String,Object> resMap=new ConcurrentHashMap<>();
        int currentStudentID=(int)request.getSession().getAttribute("student_id");
        CourseRecord record=new CourseRecord();
        record.setStudentID(currentStudentID);
        record.setTimetableID(timetableID);
        String res=CourseRecordDao.addCourseRecord(record);
        resMap.put("success",res);
        resMap.put("data",record);
        return resMap;
    }







}
