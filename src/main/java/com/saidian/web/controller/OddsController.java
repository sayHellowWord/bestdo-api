package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.GoodsType;
import com.saidian.web.bean.GoogDetail;
import com.saidian.web.platform.PublicService;
import org.apache.commons.lang.StringUtils;
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
//
//    @RequestMapping("/index")
//    public String index(ModelMap map) {
//        //运动类型
//        ResultBean goodsResultBean = null;
//        try {
//            goodsResultBean = bTiemService.getGoodsByCardId(HttpParams.cardId);
//        } catch (Exception e) {
//            logger.error(LOG_PRE + "获取运动类型出错");
//            e.printStackTrace();
//        }
//        List<GoodsType> goodsTypes = null;
//        if (null != goodsResultBean && null != goodsResultBean.getLists()) {
//            goodsTypes = goodsResultBean.getLists();
//        }
//        //行政区
//        ResultBean regionsResultBean = null;
//        try {
//            regionsResultBean = publicService.regionGetChildren(HttpParams.cityId);
//        } catch (Exception e) {
//            logger.error(LOG_PRE + "获取行政区出错");
//            e.printStackTrace();
//        }
//        //获取经纬度
//        ResultBean lntAndLatResultBean = null;
//        try {
//            //lntAndLatResultBean = publicService.getCityLngAndLat(HttpParams.cityId);
//            lntAndLatResultBean = publicService.getCityLngAndLat("52");
//        } catch (Exception e) {
//            logger.error(LOG_PRE + "获取距离出错出错");
//            e.printStackTrace();
//        }
//
//
//        map.addAttribute("goodsTypes", goodsTypes);
//        map.addAttribute("regions", new JSONArray(regionsResultBean.getData()).toList());
//        map.addAttribute("coordinate", new JSONObject(lntAndLatResultBean.getData().toString()));
//        return "odds/index";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "search")
//    public Object search(String merid, Integer page, Integer pagesize, Integer district) throws Exception {
//
//
//        //TODO 调用cms接口获取场馆id
//        String ids = "10205351000000;10205341000000;";
//
//        ResultBean resultBean = new ResultBean();
//        resultBean.setCode(200);
//        List<GoogDetail> list = new ArrayList<GoogDetail>();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String[] idArr =  ids.split(";");
//
//        for (String mer_item_id :idArr) {
//            String detailResultBean = bTiemService.itemGetMerchandiseItemInfo(mer_item_id);
//            JSONObject dataJsonObject = new JSONObject(detailResultBean);
//            dataJsonObject = new JSONObject(dataJsonObject.getString("data"));
//            GoogDetail googDetail = null;
//            try {
//                googDetail = objectMapper.readValue(dataJsonObject.toString(), GoogDetail.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (null != googDetail)
//                list.add(googDetail);
//        }
//        resultBean.setTotal(idArr.length);
//        resultBean.setLists(list);
//
//        return resultBean;
//    }




    /**
     * 场馆列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "index")
    public String index(ModelMap map) {
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



    /**
     * 搜索
     *
     * @param merid        商品ID
     * @param mer_item_ids 商品明细ids(多个用逗号分隔)
     * @param mer_price_id 商品价格ID
     * @param city         城市ID
     * @param q            关键字--搜索
     * @param card_type_id 卡种id（必填）
     * @param radius       距离
     * @param longitude    经度
     * @param latitude     纬度
     * @param sort         距离排序
     * @param price_sort   价格排序
     * @param page         页数
     * @param pagesize     页大小
     * @param district     区域选
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "search")
    public Object search(String merid, String mer_item_ids, String city, String q, String card_type_id,
                         String radius, String longitude, String latitude, String sort, String price_sort, Integer page,
                         Integer pagesize, Integer district) throws Exception {
        //根据merid获取priceid
        ResultBean resultBean = bTiemService.getMerPriceId(merid, HttpParams.oddscardId);
        String mer_price_id = "";
        if (200 == resultBean.getCode()) {
            mer_price_id = new JSONObject(resultBean.getData().toString()).getString("price_id");
        }

        ResultBean goodsDetailResultBean = bTiemService.getMerItemList(merid, "", mer_price_id,
                HttpParams.cityId, "", "", radius, longitude, latitude, "", "",
                null == page ? HttpParams.DEFAULT_PAGE : page, HttpParams.DEFAULT_PAGE_SIZE, null == district ? 0 : district);

        //讲接口返回jsondata 置空
        goodsDetailResultBean.setData(StringUtils.EMPTY);
        return goodsDetailResultBean;
    }

}
