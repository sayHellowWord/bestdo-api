package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.GoodsType;
import com.saidian.web.bean.GoogDetail;
import com.saidian.web.platform.PublicService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 特惠
 */
@RequestMapping(value = "odds")
@Controller
public class OddsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String LOG_PRE = "odds-error:";

    @Autowired
    PublicService publicService;//公共服务

    @Autowired
    BTiemService bTiemService;//场馆

    @RequestMapping("/index")
    public String index(ModelMap map) {
        //运动类型
        ResultBean goodsResultBean = null;
        try {
            goodsResultBean = bTiemService.getGoodsByCardId(HttpParams.cardId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取运动类型出错");
            e.printStackTrace();
        }
        List<GoodsType> goodsTypes = null;
        if (null != goodsResultBean && null != goodsResultBean.getLists()) {
            goodsTypes = goodsResultBean.getLists();
        }
        //行政区
        ResultBean regionsResultBean = null;
        try {
            regionsResultBean = publicService.regionGetChildren(HttpParams.cityId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取行政区出错");
            e.printStackTrace();
        }
        //获取经纬度
        ResultBean lntAndLatResultBean = null;
        try {
            //lntAndLatResultBean = publicService.getCityLngAndLat(HttpParams.cityId);
            lntAndLatResultBean = publicService.getCityLngAndLat("52");
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取距离出错出错");
            e.printStackTrace();
        }


        map.addAttribute("goodsTypes", goodsTypes);
        map.addAttribute("regions", new JSONArray(regionsResultBean.getData()).toList());
        map.addAttribute("coordinate", new JSONObject(lntAndLatResultBean.getData().toString()));
        return "odds/index";
    }

    @ResponseBody
    @RequestMapping(value = "search")
    public Object search(String merid, Integer page, Integer pagesize, Integer district) throws Exception {


        //TODO 调用cms接口获取场馆id
        String ids = "10205351000000;10205341000000;";

        ResultBean resultBean = new ResultBean();
        resultBean.setCode(200);
        List<GoogDetail> list = new ArrayList<GoogDetail>();

        ObjectMapper objectMapper = new ObjectMapper();

        String[] idArr =  ids.split(";");

        for (String mer_item_id :idArr) {
            String detailResultBean = bTiemService.itemGetMerchandiseItemInfo(mer_item_id);
            JSONObject dataJsonObject = new JSONObject(detailResultBean);
            dataJsonObject = new JSONObject(dataJsonObject.getString("data"));
            GoogDetail googDetail = null;
            try {
                googDetail = objectMapper.readValue(dataJsonObject.toString(), GoogDetail.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != googDetail)
                list.add(googDetail);
        }
        resultBean.setTotal(idArr.length);
        resultBean.setLists(list);

        return resultBean;
    }
}
