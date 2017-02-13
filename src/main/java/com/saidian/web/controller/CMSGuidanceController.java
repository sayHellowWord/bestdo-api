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
 * 健身指导
 */
@RequestMapping(value = "/cms/guidance")
@Controller
public class CMSGuidanceController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RESTClient restClient;

    @RequestMapping("/list")
    public String list() {
        return "/guidance/list";
    }

    @RequestMapping("/list/cms")
    @ResponseBody
    public Object guidanceList(Integer page, Integer rows) {
        String resultStr = restClient.guidanceList(null == page ? 1 : page, null == rows ? 10 : rows);
        ObjectMapper objectMapper = new ObjectMapper();
        Result<PageModel> pageModelResult = null;
        try {
            pageModelResult = objectMapper.readValue(resultStr, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageModelResult;
    }

    @RequestMapping("/toDetail")
    public String detail(String id, ModelMap modelMap) {
        String resultStr = restClient.guidance(id);
        Result<PhyContext> phyContextResult = null;
        try {
            phyContextResult = objectMapper.readValue(resultStr, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("phyContext", phyContextResult.getData());
        return "/guidance/detail";
    }


}
