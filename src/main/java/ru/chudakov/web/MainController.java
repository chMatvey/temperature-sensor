package ru.chudakov.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = {
            "/",
            "/sensor"
    })
    public String getPage(){
        return "index";
    }
}
