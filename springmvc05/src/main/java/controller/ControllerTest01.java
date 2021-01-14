package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 15:17
 */
@Controller
public class ControllerTest01 {

    @RequestMapping("/maserhe/t1")
    public String test0(Model model) {

        model.addAttribute("msg", "这里是直接跳转的");
        return "/WEB-INF/jsp/test.jsp";
    }
    @RequestMapping("/maserhe/t2")
    public String test1(Model model) {

        model.addAttribute("msg", "这里是也是直接跳转的");

        return "forward:/WEB-INF/jsp/test.jsp";
    }

    @RequestMapping("/maserhe/t3")
    public String test2(Model model) {

        model.addAttribute("msg", "这里是也是直接跳转的");

        return "redirect:/test1.jsp";
    }

}
