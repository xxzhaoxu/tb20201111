package com.zx.formdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }
}
