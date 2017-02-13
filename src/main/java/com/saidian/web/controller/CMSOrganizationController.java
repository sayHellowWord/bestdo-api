package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.saidian.bean.Result;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.Dynamic;
import com.saidian.web.bean.cms.MatchDynamic;
import com.saidian.web.bean.cms.Organization;
import com.saidian.web.platform.PublicService;
import org.joda.time.DateTime;
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
 * 体育组织
 */
@RequestMapping(value = "/cms/organization")
@Controller
public class CMSOrganizationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String LOG_PRE = "organization-error:";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RESTClient restClient;

    @Autowired
    PublicService publicService;//公共服务

    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        //行政区
        ResultBean regionsResultBean = null;
        try {
            regionsResultBean = publicService.regionGetChildren(HttpParams.cityId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取运动类型出错");
            e.printStackTrace();
        }
        modelMap.addAttribute("regions", new JSONArray(regionsResultBean.getData()).toList());
        return "/organization/list";
    }

    @RequestMapping("/list/cms")
    @ResponseBody
    public Object organizationList(String name, String district, String state, Integer page, Integer rows) {
        String resultStr = restClient.organizationList(Strings.isNullOrEmpty(name) ? "" : name, Strings.isNullOrEmpty(district) ? "" : district,
                Strings.isNullOrEmpty(state) ? "" : state, null == page ? 1 : page, null == rows ? 10 : rows);
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
    public String toDetail(String id, Integer page, Integer rows, ModelMap modelMap) {

        String resultStr = restClient.findOrganization(id);
        Organization organization = null;
        try {
            organization = objectMapper.readValue(resultStr, Organization.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        resultStr = restClient.findOrganizationDynamic(id, null == page ? 1 : page, null == rows ? 10 : rows);
  /*        List<Dynamic> dynamics = null;
      try {
            dynamics = objectMapper.readValue(resultStr, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        List<Dynamic> dynamics = new ArrayList<Dynamic>();
        try {
            JSONArray jsonArray =new JSONArray(resultStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject matchDynamicJson = jsonArray.getJSONObject(i);
                Dynamic dynamic = objectMapper.readValue(matchDynamicJson.toString(), Dynamic.class);
                dynamic.setTimeStr(new DateTime(dynamic.getCreateDate()).toString("yyyy-MM-dd hh:mm:ss"));
                dynamics.add(dynamic);
            }
        } catch (IOException e) {
            logger.error(LOG_PRE + "获取赛事动态列表出错");
            e.printStackTrace();
        }

        modelMap.addAttribute("organization", organization);
        modelMap.addAttribute("dynamics", dynamics);

        return "/organization/detail";
    }

    @RequestMapping("/toDynamicDetail")
    public String toDynamicDetail(String id, ModelMap modelMap) {
        String resultStr = restClient.findDynamic(id);
        Dynamic dynamic = null;
        try {
            dynamic = objectMapper.readValue(resultStr, Dynamic.class);
            dynamic.setTimeStr(new DateTime(dynamic.getCreateDate()).toString("yyyy-MM-dd"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("dynamic", dynamic);
        return "/organization/sport-organization";
    }


}
