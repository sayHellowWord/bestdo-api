package com.saidian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/11.
 */
@RequestMapping(value = "/cms/exercisehoop")
@Controller
public class CMSExerciseHoopController {

    @RequestMapping("/list")
    public String matchList(ModelMap modelMap) {
        return "/exercisehoop/list";
    }
}
