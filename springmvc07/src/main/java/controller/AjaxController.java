package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-15 19:44
 */
@Controller
public class AjaxController {

    @RequestMapping("/test0")
    public String test0() {

        return "hello";
    }


    @RequestMapping("/a")
    @ResponseBody
    public String test1(String name) {

        return "hello";
    }


    @RequestMapping("/user")
    @ResponseBody
    public List<User> test2() {

        List<User> list = new ArrayList<>();

        list.add(new User("Maserhe", 13, "Man"));
        list.add(new User("Maserhe", 13, "Man"));
        list.add(new User("Maserhe", 13, "Man"));
        list.add(new User("Maserhe", 13, "Man"));

        return list;
    }

    @RequestMapping("/login")
    @ResponseBody
    public String test3(String username) {

        if (username.equals("Maserhe")) return "Maserhe已经被注册了";
        return "";

    }

}
