package com.saidian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/7.
 */
@RequestMapping(value = "login")
@Controller
public class LoginController {

    @RequestMapping(value = "register")
    public String accountRegister() throws Exception {
        return "login/register";
    }

}
