package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/11.
 */
@RequestMapping(value = "/cms/train")
@Controller
public class CMSTrainController {

    @Autowired
    RESTClient restClient;

    @RequestMapping("/list")
    public String matchList(ModelMap modelMap) {
        return "/train/list";
    }

    /**
     * 体育培训列表 接口
     *
     * @param keyword
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */

    @RequestMapping("/list/yc")
    @ResponseBody
    public Result ycTrainList(String name, String project, String district, String signState, String shelves, String state, Integer page, Integer rows) throws Exception {
        String result = restClient.ycTrainhList(name,project,district,signState,shelves,state, null == page ? 1 : page, null == rows ? 10 : rows);
        ObjectMapper objectMapper = new ObjectMapper();
        Result matchResult = null;
        try {
            matchResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchResult;
    }

}
