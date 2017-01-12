package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.Match;
import com.saidian.web.bean.cms.MatchDynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/11.
 */
@RequestMapping(value = "cms/match")
@Controller
public class CMSMatchController {

    @Autowired
    RESTClient restClient;

    @RequestMapping("/list")
    public String matchList(ModelMap modelMap) {
        return "/match/list";
    }

    private   ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 体育赛事详情
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/detail")
    public String detail(String id,ModelMap modelMap) {
        String result = restClient.matchDetail(id);
        Result<Match> matchDetailResult = null;
        try {
            matchDetailResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = restClient.matchDynamicList(id,1,15);
        Result<MatchDynamic> matchDynamicResult = null;
        try {
            matchDynamicResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("detail",matchDetailResult.getData());
        modelMap.addAttribute("dynamic",matchDynamicResult.getData());
        return "/match/detail";
    }

    @RequestMapping("/dynamicDetail")
    public String dynamicDetail(String id,String matchName,ModelMap modelMap) {
        String  result = restClient.matchDynamicDetail(id);
        Result<MatchDynamic> matchDynamicResult = null;
        try {
            matchDynamicResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("dynamic",matchDynamicResult.getData());
        modelMap.addAttribute("matchName",matchName);
        return "/match/dynamicdetail";
    }

    /**
     * 体育赛事列表 接口
     * @param keyword
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/yc")
    @ResponseBody
    public Result<Match> ycMatchList(String keyword, Integer page, Integer rows) throws Exception {
        String result = restClient.matchList(keyword, null == page ? 1 : page, null == rows ? 10 : rows);
        ObjectMapper objectMapper = new ObjectMapper();
        Result<Match> matchResult = null;
        try {
            matchResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchResult;
    }


}
