package com.ohgiraffers.intranet.sign.controller;

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



        mv.setViewName("sign/signWaitingList");

        return mv;
    }

    @GetMapping("/registSelect")
    public ModelAndView signRegistSelect(ModelAndView mv){

        mv.setViewName("sign/signRegistSelectForm");

        return mv;
    }

    @GetMapping("/requestList")
    public ModelAndView signRequestList(ModelAndView mv){

        mv.setViewName("sign/signRequestList");

        return mv;
    }

    @GetMapping("/tempList")
    public ModelAndView signTempList(ModelAndView mv){

        mv.setViewName("sign/signTempList");

        return mv;
    }

    @GetMapping("/progressList")
    public ModelAndView signProgressList(ModelAndView mv){

        mv.setViewName("sign/signProgressList");

        return mv;
    }

    @GetMapping("/completedList")
    public ModelAndView signCompletedList(ModelAndView mv){

        mv.setViewName("sign/signCompletedList");

        return mv;
    }

    @GetMapping("/refusedList")
    public ModelAndView signRefusedList(ModelAndView mv){

        mv.setViewName("sign/signRefusedList");

        return mv;
    }

    @GetMapping("/referenceList")
    public ModelAndView signReferenceList(ModelAndView mv){

        mv.setViewName("sign/signReferenceList");

        return mv;
    }

    @GetMapping("/teamList")
    public ModelAndView signTeamList(ModelAndView mv){

        mv.setViewName("sign/signTeamList");

        return mv;
    }
}
