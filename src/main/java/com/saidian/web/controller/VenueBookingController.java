package com.saidian.web.controller;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableBiMap;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.GoodsType;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/7.
 */
@RequestMapping(value = "site")
@Controller
public class VenueBookingController {

    @Autowired
    BTiemService bTiemService;

    /**
     * 场馆列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "index")
    public String index(ModelMap map) throws Exception {

        //运动类型
        ResultBean goodsResultBean = bTiemService.getGoodsByCardId(HttpParams.cardId);

        //todo 运动类型为空 是否默认
        List<GoodsType> goodsTypes = goodsResultBean.getLists();

        ResultBean goodsDetailResultBean = bTiemService.getMerItemList("", "", "", "", "", HttpParams.cardId,
                "", "", "", "", "", HttpParams.DEFAULT_PAGE, HttpParams.DEFAULT_PAGE_SIZE, 0);

        //讲接口返回jsondata 置空
        goodsDetailResultBean.setData(StringUtils.EMPTY);

        map.addAttribute("goodsTypes", goodsTypes);
        map.addAttribute("goodsDetailResultBean", goodsDetailResultBean);

        return "site/index";
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
    public Object search(String merid, String mer_item_ids, String mer_price_id, String city, String q, String card_type_id,
                         String radius, String longitude, String latitude, String sort, String price_sort, Integer page,
                         Integer pagesize, Integer district) throws Exception {

        ResultBean goodsDetailResultBean = bTiemService.getMerItemList(merid, "", "", "", "", HttpParams.cardId,
                "", "", "", "", "", null == page ? HttpParams.DEFAULT_PAGE : page, HttpParams.DEFAULT_PAGE_SIZE, 0);

        //讲接口返回jsondata 置空
        goodsDetailResultBean.setData(StringUtils.EMPTY);

        return goodsDetailResultBean;
    }


    @RequestMapping(value = "toDetail")
    public String toDetail(String mer_item_id, String mer_price_id, ModelMap map) throws Exception {

        String detailResultBean = bTiemService.itemGetMerchandiseItemInfo(mer_item_id);
        JSONObject dataJsonObject = new JSONObject(detailResultBean);

        dataJsonObject = new JSONObject(dataJsonObject.getString("data"));

        map.addAttribute("detail", dataJsonObject);

        //场馆服务
        JSONObject stadium = dataJsonObject.getJSONObject("venue").getJSONObject("stadium");
        if (stadium.has("stadium_services")) {
            List<Map> stadium_services = new ArrayList<Map>();
            JSONObject stadiumServices = stadium.getJSONObject("stadium_services");
            for (String key : stadiumServices.keySet()) {
                stadium_services.add(ImmutableBiMap.of("name", stadiumServices.getJSONObject(key).getString("name"), "stadium_service_id", stadiumServices.getJSONObject(key).getString("stadium_service_id")));
            }
            map.addAttribute("stadium_services", stadium_services);
        }

        map.addAttribute("mer_item_id", mer_item_id);
        map.addAttribute("mer_price_id", Strings.isNullOrEmpty(mer_price_id) ? "" : mer_price_id);

        return "site/detail";
    }

    /**
     * 获取八天价格汇总以及库存汇总(乒羽篮网）
     *
     * @param mer_item_id
     * @param mer_price_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "priceAndInventorySummaryCommon")
    public ResultBean priceAndInventorySummaryCommon(String mer_item_id, String mer_price_id) throws Exception {

        System.out.println("================    获取八天价格汇总以及库存汇总(乒羽篮网）    =======================");
        ResultBean resultBean = bTiemService.priceAndInventorySummaryCommon(mer_item_id, mer_price_id);
        return bTiemService.priceAndInventorySummaryCommon(mer_item_id, mer_price_id);
    }


}
