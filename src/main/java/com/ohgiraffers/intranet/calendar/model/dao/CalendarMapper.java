package com.ohgiraffers.intranet.calendar.model.dao;

import com.ohgiraffers.intranet.calendar.model.dto.CalendarDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CalendarMapper {

//    /* DB 캘린더 전체 조회 */
//    List<CalendarDTO> selectAllCalendar();

    /* 전체조회용 메소드 */
    List<CalendarDTO> findAllCal(Map<String, String> maps);

    /* 캘린더 메인 화면 */
    List<CalendarDTO> findAllSc();

    /* 캘린더 작성 */
    int insertList(CalendarDTO calendar);

    /* 캘린더 상세조회 */
    CalendarDTO selectCdDetail(String id);

    /* 캘린더 수정 */
    int updateList(CalendarDTO calendar);
    
    /* 캘린더 삭제 */
    int cdDelete(String id);

}
