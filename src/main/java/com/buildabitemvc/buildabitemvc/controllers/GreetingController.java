package com.buildabitemvc.buildabitemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GreetingController {

    @GetMapping("/")
    public String renderIndex(){
        return "index";
    }
}
