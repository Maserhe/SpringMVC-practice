package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-15 19:44
 */
@Controller
public class AjaxController {

    @RequestMapping("/test0")
    public String test() {

        return "hello";
    }

}
