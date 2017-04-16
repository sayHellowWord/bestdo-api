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
import com.saidian.web.bean.cms.TenMinSite;
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
 * 十分钟健身圈
 */
@RequestMapping(value = "/cms/exercisehoop")
@Controller
public class CMSExerciseHoopController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String LOG_PRE = "exercisehoop-error:";

    @Autowired
    PublicService publicService;//公共服务

    @Autowired
    RESTClient restClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/list")
    public String list(ModelMap modelMap) {

        //行政区
        ResultBean regionsResultBean = null;
        try {
            regionsResultBean = publicService.regionGetChildren(HttpParams.cityId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取行政区出错");
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


        modelMap.addAttribute("page", HttpParams.DEFAULT_PAGE_CMS);
        modelMap.addAttribute("pagesize", HttpParams.DEFAULT_PAGE_SIZE_CMS);
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
        modelMap.addAttribute("tenMinSite", tenMinSite.getData());
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
    public Result<TenMinSite> ycExerciseHoopList(String firstType,String scop, String keyword,  Integer page, Integer rows) throws Exception {

        System.out.println("firstType = [" + firstType + "], scop = [" + scop + "], " +
                "keyword = [" + keyword + "], page = [" + page + "], rows = [" + rows + "]");

        String result = restClient.excrciseHoodList(Strings.isNullOrEmpty(firstType) ? "" : firstType,Strings.isNullOrEmpty(scop) ? "" : scop,
                Strings.isNullOrEmpty(keyword) ? "" : keyword, null == page ? HttpParams.DEFAULT_PAGE_CMS : page,
                null == rows ? HttpParams.DEFAULT_PAGE_SIZE_CMS : rows);
        ObjectMapper objectMapper = new ObjectMapper();
        Result<TenMinSite> tenMinSiteResult = null;
        try {
            tenMinSiteResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = restClient.excrciseHoodListTotal(Strings.isNullOrEmpty(keyword) ? "" : keyword, null == page ? HttpParams.DEFAULT_PAGE_CMS : page,
                null == rows ? HttpParams.DEFAULT_PAGE_SIZE_CMS : rows);

        Result<Integer> total = null;
        try {
            total = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != total && 200 == total.getCode()) {
            tenMinSiteResult.setTotal(total.getData());
        }
        return tenMinSiteResult;
    }


    @RequestMapping(value = "map")
    public String map(String name, String address, String latitude, String longitude, ModelMap map) throws Exception {

        map.addAttribute("name", name);
        map.addAttribute("address", address);
        map.addAttribute("latitude", latitude);
        map.addAttribute("longitude", longitude);

        return "exercisehoop/map";
    }

}
