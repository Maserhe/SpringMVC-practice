package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 2:49
 */
public class ControllerTest0 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "Maserhe is handsome and smart");
        modelAndView.setViewName("test");

        return modelAndView;

    }
}
