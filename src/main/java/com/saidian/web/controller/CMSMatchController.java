package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Doubles;
import com.saidian.bean.Result;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.config.RESTClient;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.GoodsType;
import com.saidian.web.bean.Region;
import com.saidian.web.bean.cms.Match;
import com.saidian.web.bean.cms.MatchDynamic;
import com.saidian.web.platform.PublicService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    BTiemService bTiemService;//场馆

    @Autowired
    private Environment env;

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

        //运动类型
        ResultBean goodsResultBean = null;
        try {
            goodsResultBean = bTiemService.getGoodsByCardId(HttpParams.oddscardId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取运动类型出错");
            e.printStackTrace();
        }
        List<GoodsType> goodsTypes = null;
        if (null != goodsResultBean && null != goodsResultBean.getLists()) {
            goodsTypes = goodsResultBean.getLists();
        }

        Map<String, GoodsType> sortGoodsType = new HashMap();
        int size = null == goodsTypes ? 0 : goodsTypes.size();
        GoodsType goodsType = null;
        for (int i = 0; i < size; i++) {
            goodsType = goodsTypes.get(i);
            if ("健身".equals(goodsType.getSport()))
                goodsType.setSport("器械健身");
            sortGoodsType.put(goodsType.getCid(), goodsType);
        }

        int total = Integer.parseInt(env.getProperty("server.goodtpe.total"));
        List<GoodsType> goodsTypesSort = new ArrayList<GoodsType>();
        for (int i = 1; i <= total; i++) {
            GoodsType goodsType1 = sortGoodsType.get(env.getProperty("server.goodtpe.sort" + i));
            if (null != goodsType1)
                goodsTypesSort.add(goodsType1);
        }

        modelMap.addAttribute("goodsTypes", goodsTypesSort);
        modelMap.addAttribute("page", HttpParams.DEFAULT_PAGE_CMS);
        modelMap.addAttribute("pagesize", HttpParams.DEFAULT_PAGE_SIZE_CMS);
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
    public String detail(String id, Integer page, Integer rows, ModelMap modelMap) {
        String result = restClient.matchDetail(id);
        Result<Match> matchDetailResult = null;
        try {
            matchDetailResult = objectMapper.readValue(result, Result.class);
            modelMap.addAttribute("detail", matchDetailResult.getData());
        } catch (IOException e) {
            logger.error(LOG_PRE + "获取赛事详情出错");
            e.printStackTrace();
        }

        result = restClient.matchDynamicList(id, null == page ? HttpParams.DEFAULT_PAGE_CMS : page,
                null == rows ? HttpParams.DEFAULT_PAGE_SIZE_CMS : rows);
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
    public Result<Match> ycMatchList(String itemCode, String regionName, String entryStatus, Integer page, Integer rows) throws Exception {
        String result = restClient.matchList(Strings.isNullOrEmpty(itemCode) ? "" : itemCode,
                Strings.isNullOrEmpty(regionName) ? "" : regionName, Strings.isNullOrEmpty(entryStatus) ? "" : entryStatus,
                null == page ? HttpParams.DEFAULT_PAGE_CMS : page,
                null == rows ? HttpParams.DEFAULT_PAGE_SIZE_CMS : rows);
        ObjectMapper objectMapper = new ObjectMapper();
        Result<Match> matchResult = null;
        try {
            matchResult = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            logger.error(LOG_PRE + "获取赛事列表出错");
            e.printStackTrace();
        }

        result = restClient.matchListTotal(Strings.isNullOrEmpty(itemCode) ? "" : itemCode,
                Strings.isNullOrEmpty(regionName) ? "" : regionName, Strings.isNullOrEmpty(entryStatus) ? "" : entryStatus);

        Result<Integer> total = null;
        try {
            total = objectMapper.readValue(result, Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != total && 200 == total.getCode()) {
            matchResult.setTotal(total.getData());
        }

        return matchResult;
    }


}
