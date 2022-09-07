package com.ohgiraffers.intranet.calendar.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import com.ohgiraffers.intranet.calendar.model.service.CalendarService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.spi.CalendarDataProvider;

@Controller
@RequestMapping("/calendar/*")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService){

        this.calendarService = calendarService;
    }
    /* 메인 불러오기 */
    @GetMapping("/main")
    public String cdMain() {

        return "calendar/cd_main";
    }

    /* 컨트롤러 전체조회용 메소드 */
    @GetMapping("/all")
    public ModelAndView findScList(ModelAndView mv) {

        List<CalendarDTO> calList = calendarService.findAllSc();

        mv.addObject("calList", calList);
        mv.setViewName("calendar/all");

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
    
    /* ajax로 값을 불러오기 위한 죄회용 메소드 */
    @GetMapping(value="findAll", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CalendarDTO> findAllList() {

//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

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


//    /* 일정추가 버튼으로 insert */
//    @RequestMapping("/insert")
//    public String insertCd(ModelAndView mv, @ModelAttribute CalendarDTO calendarDTO, RedirectAttributes rttr) {
//
//
//    }

    /* 일정추가 버튼으로 insert 페이지 이동 */
    @GetMapping("/insert")
    public String insertMain() {

        return "calendar/cd_insert";
    }

    /* 일정추가 insert 동작하는 메소드 */
    @PostMapping("/insert")
    public String insertList(@ModelAttribute CalendarDTO calendar) {

        System.out.println("호출확인용");
        System.out.println("calendar = " + calendar);
      //  calendar.getMemNum();

        /* 작성날짜를 입력. 이 때 작성날짜는 HTML에서 처리할 필요 없다. */
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.setwDate(sdf.format(date));
        int result = calendarService.insertList(calendar);

        System.out.println("result = " + result);
        
        return "redirect:/calendar/main";
    }

    /* 캘린더 상세조회 메소드 */
    @GetMapping("/detail")
    public ModelAndView detailMain(HttpServletRequest request, ModelAndView mv) {

        /* 원하는 데이터 불러오기 */
        String no = request.getParameter("no");
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        System.out.println("id 확인용 = " + id);
        System.out.println("no 확인용 = " + no);
        System.out.println("title 확인용 = " + title);

        /* 불러온 값 담아줌 */
        CalendarDTO cdDetail = calendarService.selectCdDetail(id);

        System.out.println("아이디 확인용 = " + id);

        mv.addObject("cdDetail", cdDetail);
        mv.setViewName("calendar/cd_detail");

        return mv;
    }


}
