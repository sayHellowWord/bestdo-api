package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * 体育信息
 */
@RequestMapping(value = "/cms/information")
@Controller
public class CMSInformationController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RESTClient restClient;

    @RequestMapping("/list")
    public String list() {
        return "/information/list";
    }

    @RequestMapping("/list/cms")
    @ResponseBody
    public Object guidanceList(String title, String label, String state, Integer page, Integer rows, String startDate, String endDate) {

        String resultStr = restClient.informationiList(title, label, state, null == page ? 1 : page, null == rows ? 10 : rows, startDate, endDate);
        List<News> newsList = null;
        try {
            newsList = objectMapper.readValue(resultStr, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setCode(200);
        result.setData(newsList);
        return result;
    }

    @RequestMapping("/toDetail")
    public String detail(String id, ModelMap modelMap) {
        String resultStr = restClient.informationiDetail(id);
        News news = null;
        try {
            news = objectMapper.readValue(resultStr, News.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("news", news);
        return "/information/detail";
    }


}
