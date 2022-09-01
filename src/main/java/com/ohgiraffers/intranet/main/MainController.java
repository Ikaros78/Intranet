package com.ohgiraffers.intranet.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/*")
public class MainController {

    @RequestMapping
    public void main(){}
}
