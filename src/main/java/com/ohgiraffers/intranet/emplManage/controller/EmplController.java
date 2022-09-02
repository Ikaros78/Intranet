package com.ohgiraffers.intranet.emplManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emplManage/*")
public class EmplController {

    @GetMapping("/emplList")
    public String emplManagePage(){

        return "emplManage/emplList";
    }
}
