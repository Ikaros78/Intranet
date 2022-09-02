package com.ohgiraffers.intranet.calendar.controller;

import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import com.ohgiraffers.intranet.calendar.model.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService){

        this.calendarService = calendarService;
    }
    
    @GetMapping("/cdmain")
    public String Cdmain() {

        return "calendar/cd_main";
    }
    @GetMapping("/all")
    public ModelAndView findScList(ModelAndView mv) {

        List<CalendarDTO> calList = calendarService.findAllSc();

        mv.addObject("calList", calList);
        mv.setViewName("/cdmain/all");

        return mv;
    }

    /* 전체 조회용 ajax를 위한 메소드 */
    @GetMapping(value="findAll", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CalendarDTO> findAllList() {

        return calendarService.findAllCal();
    }
//
//    /* DB 전체 조회용 */
//    @GetMapping(value="cdAllMain", produces = "application/json; charset=UTF-8")
//    @RequestBody // 비동기처리
//    public List<CalendarDTO> selectAllCalendar() {
//
//        System.out.println("calendarService = " + calendarService);
//        return calendarService.selectAllCalendar();
//    }

}
