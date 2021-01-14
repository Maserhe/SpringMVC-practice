package controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-14 13:09
 */
@Controller
public class ControllerTest03 {

    @RequestMapping(value = "add/{a}/{b}", method = RequestMethod.GET)
    public String add0(@PathVariable int a, @PathVariable int b, Model model) {

        model.addAttribute("msg", "test0结果是" + String.valueOf(a + b));
        return "test";
    }

    @RequestMapping(value = "add/{a}/{b}", method = RequestMethod.POST )
    public String add1(@PathVariable int a, @PathVariable int b, Model model ){

        model.addAttribute("msg", "test1结果是" + String.valueOf(a + b));
        return "test";
    }
}
