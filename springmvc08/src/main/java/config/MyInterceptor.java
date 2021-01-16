package config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-16 20:28
 */
public class MyInterceptor implements HandlerInterceptor {

    // return true 执行下一个拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 放行判断
        // 判断用户是否已经登陆
        // 如果session中没有 就转发到 登陆界面
        if (request.getRequestURI().contains("login")) return true;
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) return true;
        else request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截后---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("清理-------------");
    }
}
