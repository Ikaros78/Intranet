package com.ohgiraffers.intranet.main.controller;

import com.ohgiraffers.intranet.main.model.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/main/*")
public class MainController {

    private MainService mainService;

    @Autowired
    public MainController(MainService mainService){
        this.mainService = mainService;
    }

    @RequestMapping
    public ModelAndView main(ModelAndView mv, Authentication authentication, HttpSession httpSession){

        String mem_id = authentication.getName();

        int mem_num = mainService.selectMemNumById(mem_id);

        httpSession.setAttribute("mem_num",mem_num);

        mv.setViewName("main/main");

        return mv;
    }

    /* userImpl을 통해 로그인 시 세션에 들고온 해당 계정의 정보를 들고 오는 방법입니다. */
    @GetMapping(value = {"/", "/main"})
    public String main(@AuthenticationPrincipal User user){

        System.out.println("user : " + user);
        return "main/main";
    }

}
