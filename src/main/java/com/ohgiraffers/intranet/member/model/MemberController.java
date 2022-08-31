package com.ohgiraffers.intranet.member.model;

import com.ohgiraffers.intranet.common.exception.member.MemberRegistException;
import com.ohgiraffers.intranet.member.model.dto.MemberDTO;
import com.ohgiraffers.intranet.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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

        return "/member/login";
    }
    @GetMapping
    public String loginFailed(){

        return "errors/loginFail";
    }

    /* 회원가입 요청을 받으면 회원가입 폼으로 이동 시킴*/
    @GetMapping
    public String memberRegist(){

        return "/member/regist";
    }

    /* 회원가입 */
    public String memberRegistInsert(@ModelAttribute MemberDTO member, HttpServletRequest request,
                                     RedirectAttributes rttr) throws MemberRegistException {

        log.info("");
        log.info("");
        log.info("[MemberController] memberRegistInsert ============");

        // 우편번호 API를 통해 받은 데이터를 spring으로 들고 오는 코드
        String address = request.getParameter("sample6_postcode")
                + request.getParameter("memAddre") + request.getParameter("memAddre2");

        // spring으로 받은 주소를 DB에 저장하는 코드
        member.setMem_address(address);

        // 비밀번호 암호화하여 DB로 저장하는 코드.
        member.setMem_pw(passwordEncoder.encode(member.getMem_pw()));

        log.info("[MemberController] registInsert Member : " + member);

        memberService.memberRegistInsert(member);
                return "redirect:/";
    }


}
