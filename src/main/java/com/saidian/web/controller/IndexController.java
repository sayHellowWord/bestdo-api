package com.saidian.web.controller;

import com.saidian.config.HttpParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/4.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String boot() {
        return "index";
    }

    @RequestMapping("/contactus")
    public String contactus() {
        return "contactus";
    }

}
