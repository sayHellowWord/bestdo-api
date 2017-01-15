package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.Result;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.Match;
import com.saidian.web.bean.cms.MatchDynamic;
import com.saidian.web.platform.PublicService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String LOG_PRE = "match-error:";

    @Autowired
    RESTClient restClient;

    @Autowired
    PublicService publicService;//公共服务

    private  ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/list")
    public String matchList(ModelMap modelMap) {
        //行政区
      /*  ResultBean regionsResultBean = null;
        try {
            regionsResultBean = publicService.regionGetChildren(HttpParams.cityId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取运动类型出错");
            e.printStackTrace();
        }
        modelMap.addAttribute("regions", new JSONArray(regionsResultBean.getData()).toList());
       */
        return "/match/list";
    }

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
