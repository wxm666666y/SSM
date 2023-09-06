package nuc.edu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjun
 * @create 2020-10-21 17:44
 */
@Controller
public class indexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
