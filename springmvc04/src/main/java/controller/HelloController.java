package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 2:12
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model model) {

        // 封装数据
        model.addAttribute("msg", "Hello,SpringMVCAnnocation, Maserhe!!!!");



        return "hello"; // 会被视图解析器处理
    }
}
