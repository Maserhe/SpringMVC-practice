package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 2:59
 */

@Controller
public class ControllerTest02 {

    @RequestMapping("/test")
    public String test0(Model model) {

        model.addAttribute("msg", "Maserhe is handSome哈哈哈哈");

        // 只要实现了 Controller 接口的类， 说明这就是一个控制器了。
        return "test";
    }

}
