package com.xlian.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/postwoman")
    public String postwoman(){
        return "index.html";
    }
}
