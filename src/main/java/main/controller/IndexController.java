package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/user")
    public String user() {
        return "index";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "index";
    }

}
