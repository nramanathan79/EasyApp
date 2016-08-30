package com.easyapp.integration.kafka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public ModelAndView countries() {
        return new ModelAndView("home");
    }
}