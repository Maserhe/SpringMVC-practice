package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-16 20:25
 */

@Controller
public class Test {

    @RequestMapping("/upload")
    public String upload(@RequestParam("file")CommonsMultipartFile file, HttpServletRequest request) {

        // 上传 路径存放的位置

        System.out.println(request.getContextPath());

        // 上传文件地址
        try {
            file.transferTo(new File(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件上传失败");
        }
        System.out.println("上传成功");
        return "redirect:/index.jsp";
    }

}
