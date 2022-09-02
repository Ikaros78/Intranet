package com.ohgiraffers.intranet.calendar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import com.ohgiraffers.intranet.calendar.model.service.CalendarService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService){

        this.calendarService = calendarService;
    }
    
    @GetMapping("/cdMain")
    public String cdMain() {

        return "calendar/cdmain";
    }
    @GetMapping("/all")
    public ModelAndView findScList(ModelAndView mv) {

        List<CalendarDTO> calList = calendarService.findAllSc();

        mv.addObject("calList", calList);
        mv.setViewName("cdMain/all");

        return mv;
    }

    /* 전체 조회용 ajax를 위한 메소드 */
//    @GetMapping(value="findAll", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String findAllList() {
//
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//        String find = gson.toJson(calendarService.findAllCal());
//        System.out.println("find = " + find);
//
//        return find;
//    }

    @GetMapping(value="findAll", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CalendarDTO> findAllList() {

        return calendarService.findAllCal();
    }

//    /* DB 전체 조회용 */
//    @GetMapping(value="cdAllMain", produces = "application/json; charset=UTF-8")
//    @ResponseBody // 비동기처리
//    public List<CalendarDTO> selectAllCalendar() {
//
//        System.out.println("calendarService = " + calendarService);
//        return calendarService.selectAllCalendar();
//    }

}
