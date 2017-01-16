package com.saidian.web.controller;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.GoodsType;
import com.saidian.web.bean.siteinfo.OneDayItemPrice;
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

import java.util.*;

/**
 * Created by Administrator on 2017/1/7.
 */
@RequestMapping(value = "site")
@Controller
public class VenueBookingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String LOG_PRE = "site-error:";

    @Autowired
    BTiemService bTiemService;//场馆

    @Autowired
    PublicService publicService;//公共服务

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
            goodsResultBean = bTiemService.getGoodsByCardId(HttpParams.cardId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取运动类型出错");
            e.printStackTrace();
        }
        List<GoodsType> goodsTypes = null;
        if (null != goodsResultBean && null != goodsResultBean.getLists()) {
            goodsTypes = goodsResultBean.getLists();
            for (GoodsType goodsType : goodsTypes) {


            }
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
            lntAndLatResultBean = publicService.getCityLngAndLat(HttpParams.cityId);
        } catch (Exception e) {
            logger.error(LOG_PRE + "获取距离出错出错");
            e.printStackTrace();
        }


        map.addAttribute("goodsTypes", goodsTypes);
        map.addAttribute("regions", new JSONArray(regionsResultBean.getData()).toList());
        map.addAttribute("lng", lntAndLatResultBean);
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
    public Object search(String merid, String mer_item_ids, String city, String q, String card_type_id,
                         String radius, String longitude, String latitude, String sort, String price_sort, Integer page,
                         Integer pagesize, Integer district) throws Exception {

        //根据merid获取priceid
        ResultBean resultBean = bTiemService.getMerPriceId(merid, HttpParams.cardId);
        String mer_price_id = "";
        if (200 == resultBean.getCode()) {
            mer_price_id = new JSONObject(resultBean.getData().toString()).getString("price_id");
        }

        ResultBean goodsDetailResultBean = bTiemService.getMerItemList(merid, "", mer_price_id,
                HttpParams.cityId, "", "", "", "", "", "", "",
                null == page ? HttpParams.DEFAULT_PAGE : page, HttpParams.DEFAULT_PAGE_SIZE, null == district ? 0 : district);

        //讲接口返回jsondata 置空
        goodsDetailResultBean.setData(StringUtils.EMPTY);
        return goodsDetailResultBean;
    }

    @RequestMapping(value = "toDetail")
    public String toDetail(String mer_item_id, String mer_price_id, String cid, ModelMap map) throws Exception {
        String detailResultBean = bTiemService.itemGetMerchandiseItemInfo(mer_item_id);
        JSONObject dataJsonObject = new JSONObject(detailResultBean);
        dataJsonObject = new JSONObject(dataJsonObject.getString("data"));
        map.addAttribute("detail", dataJsonObject);

        //场馆服务
        JSONObject stadium = dataJsonObject.getJSONObject("venue").getJSONObject("stadium");
        if (stadium.has("stadium_services") && stadium.optJSONObject("stadium_services") != null) {
            List<Map> stadium_services = new ArrayList<Map>();
            JSONObject stadiumServices = stadium.getJSONObject("stadium_services");
            for (String key : stadiumServices.keySet()) {
                stadium_services.add(ImmutableBiMap.of("name", stadiumServices.getJSONObject(key).getString("name"), "stadium_service_id", stadiumServices.getJSONObject(key).getString("stadium_service_id")));
            }
            map.addAttribute("stadium_services", stadium_services);
        }

        map.addAttribute("mer_item_id", mer_item_id);
        map.addAttribute("mer_price_id", Strings.isNullOrEmpty(mer_price_id) ? "" : mer_price_id);
        map.addAttribute("cid", Strings.isNullOrEmpty(cid) ? "" : cid);
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
    public ResultBean priceAndInventorySummaryCommon(String mer_item_id, String mer_price_id, String cid) throws Exception {
        return bTiemService.priceAndInventorySummaryCommon(mer_item_id, mer_price_id, cid);
    }

    /**
     * 获取商品明细详情展示的日期(日期型和时段型)
     *
     * @param mer_item_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "showBookDays")
    public ResultBean showBookDays(String mer_item_id) throws Exception {
        return bTiemService.showBookDays(mer_item_id);
    }

    /**
     * 获取一天商品明细的价格和库存信息（日期、时段、小时）
     *
     * @param mer_item_id
     * @param mer_price_id
     * @param date
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "getOneDayItemPrice")
    public ResultBean getOneDayItemPrice(String mer_item_id, String mer_price_id, String date) throws Exception {
        return bTiemService.getOneDayItemPrice(mer_item_id, mer_price_id, date);
    }

    /**
     * 跳转到小时型库存页面
     *
     * @return
     */
    @RequestMapping(value = "toOneDayMerItemPrice")
    public String toOneDayMerItemPrice(String mer_item_id, String mer_price_id, String cid, String day, ModelMap modelMap) throws Exception {

        //获取八天价格汇总以及库存汇总(乒羽篮网）
        ResultBean priceAndInventorySummaryCommon = bTiemService.priceAndInventorySummaryCommon(mer_item_id, mer_price_id, cid);

        modelMap.addAttribute("mer_item_id", mer_item_id);
        modelMap.addAttribute("mer_price_id", mer_price_id);
        modelMap.addAttribute("day", day);
        modelMap.addAttribute("cid", cid);
        modelMap.addAttribute("priceAndInventorySummaryCommon", priceAndInventorySummaryCommon.getLists());

        return "/site/onedaymeritemprice";
    }

    //获取日期型 某一天各片场信息
    @RequestMapping(value = "getOneDayItemPriceForTimeinterval")
    @ResponseBody
    public Object getOneDayItemPriceForTimeinterval(String mer_item_id, String mer_price_id, String day, ModelMap modelMap) throws Exception {

        ResultBean<OneDayItemPrice> oneDayMerItemPrice = bTiemService.getOneDayItemPriceForTimeinterval(mer_item_id, mer_price_id, day);
        OneDayItemPrice oneDayItemPrice = oneDayMerItemPrice.getObject();

        //片场库存信息
        List<OneDayItemPrice.InventoryInfo> inventoryInfos = oneDayItemPrice.getInventoryInfos();

        //安装片场名称排序
        //排序器
        Ordering<OneDayItemPrice.InventoryInfo> orderingName = new Ordering<OneDayItemPrice.InventoryInfo>() {
            public int compare(OneDayItemPrice.InventoryInfo left, OneDayItemPrice.InventoryInfo right) {
                return left.getName().compareTo(right.getName());
            }
        };
        inventoryInfos = orderingName.sortedCopy(inventoryInfos);

        List<String> nameList = new ArrayList<String>();//片场名称
        List<String> timeList = new ArrayList<String>();//时间段名称

        //初始化时间段
        if (null != inventoryInfos && inventoryInfos.size() > 0) {
            OneDayItemPrice.InventoryInfo inventoryInfo = inventoryInfos.get(0);
            List<OneDayItemPrice.HourInfo> hourInfos = inventoryInfo.getHourInfos();
            for (OneDayItemPrice.HourInfo hourInfo : hourInfos) {
                if (hourInfo.getHour() < 10) {
                    timeList.add("0" + hourInfo.getHour() + ":00");
                } else {
                    timeList.add(hourInfo.getHour() + ":00");
                }
            }
            //排序
            Collections.sort(timeList);
        }

        int rowsNum = timeList.size();
        //一个map为一行数据
        List<Map<String, List<OneDayItemPrice.HourInfo>>> rows = new ArrayList<Map<String, List<OneDayItemPrice.HourInfo>>>(rowsNum);

        //初始化行
        for (int i = 0; i < rowsNum; i++) {
            rows.add(new HashMap<String, List<OneDayItemPrice.HourInfo>>());
        }

        //排序器
        Ordering<OneDayItemPrice.HourInfo> orderingHour = new Ordering<OneDayItemPrice.HourInfo>() {
            public int compare(OneDayItemPrice.HourInfo left, OneDayItemPrice.HourInfo right) {
                return Ints.compare(left.getHour(), right.getHour());
            }
        };

        for (OneDayItemPrice.InventoryInfo inventoryInfo : inventoryInfos) {
            //初始化片场名称
            nameList.add(inventoryInfo.getName());

            //时间点、可预订状态
            List<OneDayItemPrice.HourInfo> hourInfos = inventoryInfo.getHourInfos();

            //排序-按照时间从早到到晚排序好（和时间段名称对应）
            hourInfos = orderingHour.sortedCopy(hourInfos);
            int hourInfosLength = hourInfos.size();
            for (int i = 0; i < hourInfosLength; i++) {
                Map<String, List<OneDayItemPrice.HourInfo>> row = rows.get(i);
                List<OneDayItemPrice.HourInfo> hourInfoList = row.get("row");
                if (null == hourInfoList) {
                    hourInfoList = new ArrayList<OneDayItemPrice.HourInfo>();
                }
                hourInfoList.add(hourInfos.get(i));
                row.put("row", hourInfoList);
                rows.set(i, row);
            }
        }
        Map<String, List> sMap = new HashMap<String, List>();
        sMap.put("timeList", timeList);
        sMap.put("nameList", nameList);
        sMap.put("rows", rows);
        return sMap;
        // return ImmutableBiMap.of("timeList", timeList, "nameList", nameList, "rows", rows);
    }


}
