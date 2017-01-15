package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.PageModel;
import com.saidian.web.bean.cms.PhyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/15.
 */
@RequestMapping(value = "/cms/nformation")
@Controller
public class CMSInformationController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RESTClient restClient;

    @RequestMapping("/list")
    public String list() {
        return "/informationi/list";
    }

    @RequestMapping("/list/cms")
    @ResponseBody
    public Object guidanceList(String title, String label, String state, Integer page, Integer rows, String startDate, String endDate) {
        String resultStr = restClient.informationiList(title, label, state, null == page ? 1 : page, null == rows ? 10 : rows, startDate, endDate);
        System.out.println(resultStr);
    /*
        Result<PageModel> pageModelResult = null;
        try {
            pageModelResult = objectMapper.readValue(resultStr, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @RequestMapping("/toDetail")
    public String detail(String id, ModelMap modelMap) {
        String resultStr = restClient.informationiDetail(id);
        Result<PhyContext> phyContextResult = null;
        try {
            phyContextResult = objectMapper.readValue(resultStr, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("phyContext", phyContextResult);
        return "/information/detail";
    }


}
