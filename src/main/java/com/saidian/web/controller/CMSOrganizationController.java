package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.Dynamic;
import com.saidian.web.bean.cms.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */
@RequestMapping(value = "/cms/organization")
@Controller
public class CMSOrganizationController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RESTClient restClient;

    @RequestMapping("/list")
    public String list() {
        return "/organization/list";
    }

    @RequestMapping("/list/cms")
    @ResponseBody
    public Object organizationList(String name, String district, String state, Integer page, Integer rows) {
        String resultStr = restClient.organizationList(name, district, state, null == page ? 1 : page, null == rows ? 10 : rows);
        Result result = new Result();
        List<Organization> organizations = null;
        try {
            organizations = objectMapper.readValue(resultStr, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setData(organizations);
        result.setCode(200);
        return result;
    }


    @RequestMapping("/toDetail")
    public String toDetail(String id, ModelMap modelMap) {
        String resultStr = restClient.findOrganization(id);
        Organization organization = null;
        try {
            organization = objectMapper.readValue(resultStr, Organization.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Dynamic> dynamics = new ArrayList<Dynamic>();
        Dynamic dynamic = new Dynamic();
        dynamic.setId(6l);
        dynamic.setTitle("赛事动态标题1");
        dynamic.setOrgnName("网球协会");
        dynamic.setOrgnId(2l);
        dynamic.setPhotoIcon("http://ojhwcmd4b.bkt.clouddn.com/1484206729390/2.jpg");
        dynamic.setShortIcon("http://ojhwcmd4b.bkt.clouddn.com/1484206731046/84Z58PICuRE_1024.jpg");
        dynamic.setDescription("123");
        dynamic.setIsShow((byte)0);
        dynamic.setAutohome((byte)0);
        dynamic.setState((byte)0);
        dynamic.setCreateDate(new Date());

        dynamics.add(dynamic);
        modelMap.addAttribute("organization", organization);
        modelMap.addAttribute("dynamics", dynamics);

        return "/organization/detail";
    }

    @RequestMapping("/toDynamicDetail")
    public String toDynamicDetail(String id, ModelMap modelMap) {

        Dynamic dynamic = new Dynamic();
        dynamic.setId(6l);
        dynamic.setTitle("赛事动态标题1");
        dynamic.setOrgnName("网球协会");
        dynamic.setOrgnId(2l);
        dynamic.setPhotoIcon("http://ojhwcmd4b.bkt.clouddn.com/1484206729390/2.jpg");
        dynamic.setShortIcon("http://ojhwcmd4b.bkt.clouddn.com/1484206731046/84Z58PICuRE_1024.jpg");
        dynamic.setDescription("123");
        dynamic.setIsShow((byte)0);
        dynamic.setAutohome((byte)0);
        dynamic.setState((byte)0);
        dynamic.setCreateDate(new Date());
        modelMap.addAttribute("dynamic", dynamic);
        return "/organization/detail";
    }


}
