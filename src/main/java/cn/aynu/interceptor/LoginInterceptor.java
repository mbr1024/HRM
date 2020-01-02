package cn.aynu.interceptor;

import cn.aynu.commons.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/3
 */
public class LoginInterceptor implements HandlerInterceptor {
    private String[] IG_URL = {"/index.jsp", "/loginForm.jsp", "/login.do"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String request_URI = request.getRequestURI();
        //System.out.println("当前请求" + request_URI);
        User user = (User) request.getSession().getAttribute("user_session");
        if (request_URI.indexOf("login") <= 0 && request_URI.indexOf("logout")<=0 ) {
            // 说明不是处在登陆界面
            //System.out.println(user);
            if (user != null) {
                //System.out.println("------登陆过了-------");
                // 登陆成功的用户
                return true;
            } else {
                // 没有登陆，转向登陆界面
                //System.out.println("-------没有登陆-----");
                request.setAttribute("message", "请先登录");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return false;
            }
        } else {
            //System.out.println("-----登陆,放行-------");
            return true;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }
}
