package com.ohgiraffers.intranet.member.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohgiraffers.intranet.common.exception.member.MemberRegistException;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.member.service.MemberService;

import lombok.Setter;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    public MemberController(PasswordEncoder passwordEncoder, MemberService memberService) {
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
    }


    /* 로그인 */
    @GetMapping("/login")
    public String memberLogin(){

        return "/common/login";
    }
    @GetMapping("/loginFail")
    public String loginFailed(){

        return "errors/loginFail";
    }


    /* 회원가입 요청을 받으면 회원가입 폼으로 이동 시킴*/
    @GetMapping("/regist")
    public String memberRegist(){

        return "/member/regist";
    }

    @PostMapping("/regist")
    /* 회원가입 */
    public String memberRegistInsert(@ModelAttribute MemberDTO member, HttpServletRequest request,
                                     RedirectAttributes rttr) throws MemberRegistException {

        log.info("");
        log.info("");
        log.info("[MemberController] memberRegistInsert ============");

        // 우편번호 API를 통해 받은 데이터를 spring으로 들고 오는 코드
        String address = request.getParameter("mem_address") + " " + request.getParameter("mem_address2");

        // spring으로 받은 주소를 DB에 저장하는 코드
        member.setMem_address(address);

        member.setMem_pw(passwordEncoder.encode(member.getMem_pw()));

        log.info("[MemberController] registInsert Member : " + member);

        memberService.memberRegistInsert(member);

        rttr.addFlashAttribute("message", "회원가입 성공. 로그인해주세요.");

                return "redirect:/";
    }

    @PostMapping("/checkDupId")
    public ResponseEntity<String> checkDupId(@RequestBody MemberDTO memberDTO) throws JsonProcessingException {

        log.info("");
        log.info("");
        log.info("[MEMBER CONTROLLER] Start Id Check.........");

        String idCheckResult = "중복된 아이디가 없습니다.";
        log.info("[MEMBER CONTROLLER] 아이디 체크 : " + memberDTO.getMem_id());

        if("".equals(memberDTO.getMem_id())){
            log.info("[MEMBER CONTROLLER] No Input Member Id");
            idCheckResult = "아이디를 입력해주십시오";

        } else if(memberService.selectCheckMember(memberDTO.getMem_id())){
            log.info("[MEMBER CONTROLLER] Check Same Id");
            idCheckResult = "중복된 아이디입니다.";
        }

        return ResponseEntity.ok(idCheckResult);


    }


}
