package course.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import course.interceptor.RequireLogin;

/**
 * 此函数为登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 处理handler;
        if (handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            //当前用户名为空，但是此方法要求验证登录,则跳转至登录界面
            if(hm.getMethodAnnotation(RequireLogin.class)!=null&&request.getSession().getAttribute("user")==null){
                response.sendRedirect("/course_web_mvc/login/login");
                return false;
            }
        }
        return super.preHandle(request,response,handler);
    }
}
