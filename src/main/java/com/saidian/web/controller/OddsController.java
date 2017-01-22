package com.saidian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 特惠
 */
@RequestMapping(value = "odds")
@Controller
public class OddsController {

    @RequestMapping("/")
    public String boot() {
        return "odds/index";
    }


}
