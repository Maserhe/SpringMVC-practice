package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 20:05
 */

@Controller
public class UserController {

    @RequestMapping(value = "/t1")
    @ResponseBody // 不会走视图解析器， 直接返回字符串。，
    public String json1() throws JsonProcessingException {

        // Jackson中 有一个 ObjectMapper
        // 创建一个对象
        User user = new User("Maserhe", 21, "男");

        return new ObjectMapper().writeValueAsString(user);
    }
    @RequestMapping("/t2")
    @ResponseBody
    public String json2() throws JsonProcessingException {

        User user = new User("Maserhe", 21, "男");
        User user1 = new User("Maserhe", 21, "男");
        User user2 = new User("Maserhe", 21, "男");
        User user3 = new User("Maserhe", 21, "男");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);

        return new ObjectMapper().writeValueAsString(list);
    }
}