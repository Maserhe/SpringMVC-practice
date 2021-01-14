package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.User;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 15:33
 */

@Controller
@RequestMapping("/user")
public class ControllerTest04 {

    @GetMapping("/t1")
    public String test1(@RequestParam("username") String name, Model model) {
        // 1. 接受前端的参数
        System.out.println("输入的用户名是： " + name);
        // 2. 将结果返回给前端
        model.addAttribute("msg", name);

        return "test";
    }

    // 传参是一个对象，表单域必须要和对象中的中的属性名一致，不然参数会为null
    @RequestMapping("/t2")
    public String test2(User user) {

        System.out.println(user);

        return "test";
    }
}
