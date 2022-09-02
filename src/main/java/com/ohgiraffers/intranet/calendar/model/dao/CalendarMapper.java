package com.ohgiraffers.intranet.calendar.model.dao;

import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMapper {

//    /* DB 캘린더 전체 조회 */
//    List<CalendarDTO> selectAllCalendar();

    /* 전체조회용 메소드 */
    List<CalendarDTO> findAllCal();

    List<CalendarDTO> findAllSc();
}
