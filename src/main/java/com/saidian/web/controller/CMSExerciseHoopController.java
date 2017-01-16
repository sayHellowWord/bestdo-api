package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.TenMinSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/11.
 */
@RequestMapping(value = "/cms/exercisehoop")
@Controller
public class CMSExerciseHoopController {

    @Autowired
    RESTClient restClient;

    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        return "/exercisehoop/list";
    }

    @RequestMapping("/toDetail")
    public String toDetail(String id, ModelMap modelMap) {
        String result = restClient.excrciseHoodDetail(id);
        ObjectMapper objectMapper = new ObjectMapper();
        Result<TenMinSite> tenMinSite = null;
        try {
            tenMinSite = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("tenMinSite",tenMinSite.getData());
        return "/exercisehoop/detail";
    }

    /**
     * 十分钟健身圈列表 接口
     *
     * @param keyword
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/yc")
    @ResponseBody
    public Result<TenMinSite> ycExerciseHoopList(String keyword, Integer page, Integer rows) throws Exception {
        String result = restClient.excrciseHoodList(Strings.isNullOrEmpty(keyword) ? "" : keyword, null == page ? 1 : page, null == rows ? 10 : rows);
        ObjectMapper objectMapper = new ObjectMapper();
        Result<TenMinSite> tenMinSiteResult = null;
        try {
            tenMinSiteResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tenMinSiteResult;
    }

}
