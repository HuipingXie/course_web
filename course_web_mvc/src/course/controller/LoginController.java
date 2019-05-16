package course.controller;

import course.dao.StudentDao;
import course.dao.TeacherDao;
import course.model.Student;
import course.model.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 实现登录的函数，如果登录成功跳转至相应页面，如果失败，则跳转至登录页
     * @return
     */
    @RequestMapping("doLogin")
    public String doLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("user_type") String userType){

        if(userType.equals("student")){
            Student student= StudentDao.getStudent(username,password);
            if(student.getStudentID()!=0){
                request.getSession().setAttribute("user",student.getName());
                request.getSession().setAttribute("student",student);
                request.getSession().setAttribute("user_type","student");
                request.getSession().setAttribute("student_id",student.getStudentID());
                return "redirect:/student/index";
            }else{
                return "redirect:/login/login";
            }
        }else if(userType.equals("teacher")){
            Teacher teacher= TeacherDao.getTeacher(username,password);
            if(teacher.getTeacherID()!=0){
                request.getSession().setAttribute("user",teacher.getName());
                request.getSession().setAttribute("teacher",teacher);
                request.getSession().setAttribute("user_type","teacher");
                request.getSession().setAttribute("teacher_id",teacher.getTeacherID());
                return "redirect:/teacher/index";
            }else{
                return "redirect:/login/login";
            }
        }
        return "redirect:/login/login";
    }

    @RequestMapping("logout")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("student");
        request.getSession().removeAttribute("teacher");
        request.getSession().removeAttribute("user_type");
        request.getSession().removeAttribute("teacher_id");
        request.getSession().removeAttribute("student_id");

        return "redirect:/login/login";
    }


}
