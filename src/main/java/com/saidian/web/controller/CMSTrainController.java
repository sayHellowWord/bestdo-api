package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Doubles;
import com.saidian.bean.Result;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.Region;
import com.saidian.web.bean.cms.Coach;
import com.saidian.web.bean.cms.Train;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 体育培训
 */
@RequestMapping(value = "/cms/train")
@Controller
public class CMSTrainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String LOG_PRE = "train-error:";

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
        List<Region> regionList = new ArrayList<Region>();
        JSONArray jsonArray = new JSONArray(regionsResultBean.getData());
        int length = jsonArray.length();
        Region region = new Region();
        for (int i = 0; i < length; i++) {
            try {
                region = objectMapper.readValue(jsonArray.get(i).toString(), Region.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            regionList.add(region);
        }
        Ordering<Region> ordering = new Ordering<Region>() {
            public int compare(Region left, Region right) {
                return Doubles.compare(left.getSequence(), right.getSequence());
            }
        };
        regionList = ordering.sortedCopy(regionList);
        modelMap.addAttribute("regions", regionList);
        return "/train/list";
    }

    @RequestMapping("/toDetail")
    public String toDetail(String id, ModelMap modelMap) {
        String result = restClient.trainDetail(id);
        Train train = null;
        try {
            train = objectMapper.readValue(result, Train.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = restClient.coachList(id, "", "", "", "", 1, 15);
        List<Coach> coaches = null;
        try {
            coaches = objectMapper.readValue(result, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("train", train);
        modelMap.addAttribute("coaches", coaches);

        return "/train/detail";
    }

    /**
     * 体育培训列表 接口
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */

    @RequestMapping("/list/yc")
    @ResponseBody
    public Result ycTrainList(String name, String project, String district, String signState, String shelves, String state, Integer page, Integer rows, String time_sort) throws Exception {

        String resultStr = restClient.ycTrainhList(name, project, Strings.isNullOrEmpty(district) ? "" : district, signState, shelves, state,
                null == page ? HttpParams.DEFAULT_PAGE_CMS : page, null == rows ? HttpParams.DEFAULT_PAGE_SIZE_CMS : rows, Strings.isNullOrEmpty(time_sort) ? "asc" : time_sort);

        Result result = new Result();
        List<Train> trains = null;
        try {
            trains = objectMapper.readValue(resultStr, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setData(trains);
        result.setCode(200);
        return result;
    }

    @RequestMapping("/coach/list")
    @ResponseBody
    public Object coachList(String name, String project, String rank, String state, Integer page, Integer rows) throws Exception {
        String resultStr = restClient.coachList("", name, project, rank, state, null == page ? HttpParams.DEFAULT_PAGE_CMS : page,
                null == rows ? HttpParams.DEFAULT_PAGE_SIZE_CMS : rows);
        List<Coach> coaches = null;
        try {
            coaches = objectMapper.readValue(resultStr, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setData(coaches);
        result.setCode(200);
        return result;
    }

    @RequestMapping("/coach/toDetail")
    public String list(String id, ModelMap modelMap) {
        String result = restClient.coachDetail(id);
        Coach coache = null;
        try {
            coache = objectMapper.readValue(result, Coach.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("coach", coache);
        return "/train/trainer-introduction";
    }

}
