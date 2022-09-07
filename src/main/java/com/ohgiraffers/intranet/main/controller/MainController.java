package com.ohgiraffers.intranet.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/main/*")
public class MainController {

    @GetMapping(value = {"/" , "/main"} )
    public ModelAndView main(ModelAndView mv){

        mv.setViewName("main/main");

        return mv;
    }

}
