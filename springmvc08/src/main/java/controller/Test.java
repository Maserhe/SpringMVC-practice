package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-16 20:25
 */

@Controller
public class Test {

    @RequestMapping("/toMain")
    public String toMain() {
        return "main";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    @RequestMapping("/login")
    public String login(HttpSession session, String username, String pwd) {
        session.setAttribute("username", "Maserhe");
        System.out.println("hahahhha");
        return "main";
    }
}
