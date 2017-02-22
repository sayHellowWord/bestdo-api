package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.saidian.bean.Result;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.Match;
import com.saidian.web.bean.cms.MatchDynamic;
import com.saidian.web.platform.PublicService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 体育赛事
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

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/list")
    public String matchList(ModelMap modelMap) {
        //行政区
        ResultBean regionsResultBean = null;
        try {
            regionsResultBean = publicService.regionGetChildren(HttpParams.cityId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取行政区出错");
            e.printStackTrace();
        }
        modelMap.addAttribute("regions", new JSONArray(regionsResultBean.getData()).toList());
        return "/match/list";
    }

    /**
     * 体育赛事详情
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/detail")
    public String detail(String id, ModelMap modelMap) {
        String result = restClient.matchDetail(id);
        Result<Match> matchDetailResult = null;
        try {
            matchDetailResult = objectMapper.readValue(result, Result.class);
            modelMap.addAttribute("detail", matchDetailResult.getData());
        } catch (IOException e) {
            logger.error(LOG_PRE + "获取赛事详情出错");
            e.printStackTrace();
        }

        result = restClient.matchDynamicList(id, 1, 15);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<MatchDynamic> matchDynamics = new ArrayList<MatchDynamic>();
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject matchDynamicJson = jsonArray.getJSONObject(i);
                MatchDynamic matchDynamic = objectMapper.readValue(matchDynamicJson.toString(), MatchDynamic.class);
                matchDynamic.setCreateTimeStr(simpleDateFormat.format(matchDynamic.getCreateTime()));
                matchDynamics.add(matchDynamic);
            }
            modelMap.addAttribute("dynamics", matchDynamics);
        } catch (IOException e) {
            logger.error(LOG_PRE + "获取赛事动态列表出错");
            e.printStackTrace();
        }

        return "/match/detail";
    }

    @RequestMapping("/dynamicDetail")
    public String dynamicDetail(String id, String matchName, ModelMap modelMap) {
        String result = restClient.matchDynamicDetail(id);
        Result<MatchDynamic> matchDynamicResult = null;
        try {
            matchDynamicResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            logger.error(LOG_PRE + "获取赛事动态出错");
            e.printStackTrace();
        }
        modelMap.addAttribute("dynamic", matchDynamicResult.getData());
        modelMap.addAttribute("matchName", matchName);
        return "/match/dynamicdetail";
    }

    /**
     * 体育赛事列表 接口
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/yc")
    @ResponseBody
    public Result<Match> ycMatchList(String district, Integer page, Integer rows) throws Exception {
        String result = restClient.matchList(Strings.isNullOrEmpty(district) ? "" : district, null == page ? 1 : page, null == rows ? 10 : rows);
        ObjectMapper objectMapper = new ObjectMapper();
        Result<Match> matchResult = null;
        try {
            matchResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            logger.error(LOG_PRE + "获取赛事列表出错");
            e.printStackTrace();
        }
        return matchResult;
    }


}
