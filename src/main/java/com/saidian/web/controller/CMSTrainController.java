package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/1/11.
 */
@RequestMapping(value = "/cms/train")
@Controller
public class CMSTrainController {

    private  ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RESTClient restClient;


    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        return "/train/list";
    }

    @RequestMapping("/toDetail")
    public String toDetail(String id, ModelMap modelMap) {
        String result = restClient.trainDetail(id);
        modelMap.addAttribute("train", new JSONObject(result));
        return "/train/detail";
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
    public Object ycTrainList(String name, String project, String district, String signState, String shelves, String state, Integer page, Integer rows, String time_sort) throws Exception {
        String resultStr = restClient.ycTrainhList(name, project, district, signState, shelves, state,
                null == page ? 1 : page, null == rows ? 10 : rows, Strings.isNullOrEmpty(time_sort) ? "asc" : time_sort);
        Result result = new Result();
        result.setData(new JSONArray(resultStr));
        result.setCode(200);
        return result;
    }

    @RequestMapping("/coach/list")
    @ResponseBody
    public Object coachList(String name, String project, String rank, String state, Integer page, Integer rows) throws Exception {
        String resultStr = restClient.coachList(name, project, rank, state, null == page ? 1 : page, null == rows ? 10 : rows);
        Result result = new Result();
        result.setData(new JSONArray(resultStr));
        result.setCode(200);
        return result;
    }

    @RequestMapping("/coach/toDetail")
    public String list(String id,ModelMap modelMap) {
        String result = restClient.coachDetail(id);
        modelMap.addAttribute("coach",new JSONObject(result));
        return "/train/coachdetail";
    }

}
