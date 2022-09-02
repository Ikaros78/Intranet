package com.ohgiraffers.intranet.calendar.model.service;

import com.ohgiraffers.intranet.calendar.model.dao.CalendarMapper;
import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private CalendarMapper calendarMapper;

    @Autowired
    public CalendarService(CalendarMapper calendarMapper) {

        this.calendarMapper = calendarMapper;
    }

//    /* DB 캘린더 전체 조회 */
//    public List<CalendarDTO> selectAllCalendar() {
//
//        return calendarMapper.selectAllCalendar();
//    }

    public List<CalendarDTO> findAllCal() {

        return calendarMapper.findAllCal();
    }

    public List<CalendarDTO> findAllSc() {

        return calendarMapper.findAllSc();
    }
}
