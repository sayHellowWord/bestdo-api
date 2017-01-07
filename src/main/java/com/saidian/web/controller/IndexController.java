package com.saidian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/4.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String boot() {
        return "index";
    }

}
