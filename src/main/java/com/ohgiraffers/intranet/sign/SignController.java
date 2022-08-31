package com.ohgiraffers.intranet.sign;

import com.ohgiraffers.intranet.sign.model.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign/*")
public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService){

        this.signService = signService;
    }

    @GetMapping(value = {"/main", "/waitingList"})
    public ModelAndView signWaitingList(ModelAndView mv){



        mv.setViewName("sign/waitingList");

        return mv;
    }
}
