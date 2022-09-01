package com.ohgiraffers.intranet.calendar.controller;

import com.ohgiraffers.intranet.calendar.model.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cd")
public class CalendarController {

    @GetMapping("/cdmain")
    public String Cdmain() {

        return "calendar/cd_main";
    }

}
