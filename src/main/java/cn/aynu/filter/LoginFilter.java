package cn.aynu.filter;

import cn.aynu.commons.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/3
 */
public class LoginFilter implements Filter {
    private String[] IG_URL = {"/index.jsp", "/loginForm.jsp", "/login.do"};

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String request_URI = request.getRequestURI();
        //System.out.println(request_URI + "请求已被拦截处理");
        for (String s : IG_URL) {
            if (request_URI.endsWith(s)) {
                //System.out.println("是可以放行的请求" + request_URI);
                filterChain.doFilter(request, response);
                return;
            }
        }

        //System.out.println("正常请求");
        User user_login = (User) request.getSession().getAttribute("user_session");
        if (user_login != null) {
           // System.out.println("已经登录");
            filterChain.doFilter(request, response);

        } else {
            //System.out.println("未登录");
            request.setAttribute("message","您未登录，请先登录");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}



