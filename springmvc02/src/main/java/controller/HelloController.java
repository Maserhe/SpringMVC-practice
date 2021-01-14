package controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-13 22:43
 */
public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        // ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();

        // 封装对象
        mv.addObject("msg", "HelloMaserhe"); // 第二个参数可以为空

        // 封装要跳转的 视图 放在modelAndView 中
        mv.setViewName("test");

        return mv;

    }

}
