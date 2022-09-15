package com.ohgiraffers.intranet.authorManage.model.dao;

import com.ohgiraffers.intranet.authorManage.model.dto.AuthoritDTO;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper {
//    List<MemberDTO> selectAuthority(String searchValue);

//    int deleteBoardAuthority(int memNum);

    List<AuthoritDTO> selectEmAuthority();

    List<AuthoritDTO> selectHrAuthority();

    List<AuthoritDTO> selectNmAuthority();
}
