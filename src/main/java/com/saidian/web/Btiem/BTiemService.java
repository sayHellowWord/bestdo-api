package com.saidian.web.Btiem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Ordering;
import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import com.saidian.web.bean.*;
import com.saidian.web.bean.siteinfo.*;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/1/5.
 */
@Service
public class BTiemService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static String MER_LISTS = "mer/lists";

    private static String MER_GET_ITEM_LIST = "mer/getMerItemList";

    //http://test.weixin.bestdo.com/mer/lists?mer_id=1020457&latitude=39.929985&longitude=116.395645&city=52&sport=life&show_price_id=1845&default_price_id=1845&radius=10000&price_sort=&district=510
    private static String LISTS = "mer/lists";

    private static String MER_GET_MERCHANDISE_DETAIL = "mer/getMerchandiseDetail";

    private static String ITEM_SHOW_BOOK_DAYS = "item/showBookDays";

    private static String MER_VALID_PRICE_TIME = "mer/getValidPriceTime";

    private static String ITEM_GET_MERCHANDISE_DETAIL = "item/getMerchandiseItemInfo";

    //获取八天价格汇总以及库存汇总(乒羽篮网）
    private static String ITEM_PRICE_INVENTORY_SUMMARY_COMMON = "item/priceAndInventorySummaryCommon";

    //获取一天商品明细的价格和库存信息（日期、时段、小时）
    private static String ITEM_ONE_DAY_IMEM_PRICE = "item/getOneDayItemPrice";

    //获取小时型库存
    private static String MER_ONE_DAY_MER_ITEM_PRICE = "item/getOneDayMerItemPrice";

    //根据商品ID及卡种ID获取默认价格ID
    private static String PROJECT_MER_PRICE_ID = "project/getMerPriceId";


    /**
     * 根据卡种id 获取商品信息（运动类型）
     *
     * @param card_type_id
     * @return
     * @throws Exception
     */
    public ResultBean getGoodsByCardId(String card_type_id) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("card_type_id", card_type_id);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + MER_LISTS, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<GoodsType> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            List<GoodsType> lists = new ArrayList<GoodsType>();
            JSONObject resultJsonObject = new JSONObject(resultBean.getData());
            for (String key : resultJsonObject.keySet()) {
                GoodsType goods = new GoodsType();
                goods.setId(key);
                JSONObject goodsJsonObject = resultJsonObject.getJSONObject(key);
                goods.setMerid(goodsJsonObject.getString("merid"));
                goods.setName(goodsJsonObject.getString("name"));
                goods.setCid(goodsJsonObject.getString("cid"));
                goods.setAlias(goodsJsonObject.getString("alias"));
                goods.setSport(goodsJsonObject.getString("sport"));
                goods.setImgurl(goodsJsonObject.getString("imgurl"));
                goods.setClient_name(goodsJsonObject.getString("client_name"));
                lists.add(goods);
            }
            resultBean.setLists(lists);
        }
        return resultBean;
    }

    /**
     * 获取商品明细列表
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
    public ResultBean getMerItemList(String merid, String mer_item_ids, String mer_price_id, String city, String q, String card_type_id,
                                     String radius, String longitude, String latitude, String sort, String price_sort, int page,
                                     int pagesize, int district) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merid", merid);
        jsonObject.put("mer_item_ids", mer_item_ids);
        jsonObject.put("mer_price_id", mer_price_id);
        jsonObject.put("city", city);
        jsonObject.put("q", q);
        jsonObject.put("card_type_id", card_type_id);
        jsonObject.put("radius", radius);
        jsonObject.put("longitude", longitude);
        jsonObject.put("latitude", latitude);
        jsonObject.put("sort", sort);
        jsonObject.put("price_sort", price_sort);
        jsonObject.put("page", page);
        jsonObject.put("pagesize", pagesize);
        jsonObject.put("district", district);


        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + MER_GET_ITEM_LIST, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<GoogDetail> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            List<GoogDetail> lists = new ArrayList<GoogDetail>();
            JSONObject resultJsonObject = new JSONObject(resultBean.getData());
            resultBean.setPage(resultJsonObject.getInt("page"));
            resultBean.setPageSize(resultJsonObject.getInt("pageSize"));
            int total = resultJsonObject.getInt("total");
            resultBean.setTotal(total);
            resultBean.setTotalPage(resultJsonObject.getInt("totalPage"));
            if (resultJsonObject.has("items") && total > 0) {
                JSONObject items = resultJsonObject.getJSONObject("items");
                for (String key : items.keySet()) {
                    GoogDetail googDetail = new GoogDetail();
                    googDetail.setId(key);
                    JSONObject goodDetailJsonObject = items.getJSONObject(key);
                    HttpResultUtil.item2GoodDetail(googDetail, goodDetailJsonObject);
                    lists.add(googDetail);
                }
            }
            resultBean.setLists(lists);
        }
        return resultBean;
    }


    /**
     * 获取商品信息
     *
     * @param merid 商品ID
     * @return
     * @throws Exception
     */
    public ResultBean merGetMerchandiseDetail(String merid) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merid", merid);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + MER_GET_MERCHANDISE_DETAIL, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<GoodsInfo> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJsonObject = new JSONObject(resultBean.getData());

            GoodsInfo goodsInfo = new GoodsInfo();

            JSONObject basicInfoJsonObject = dataJsonObject.getJSONObject("basicInfo");

            GoodsInfoBasicInfo goodsInfoBasicInfo = new GoodsInfoBasicInfo();
            goodsInfoBasicInfo.setMerid(basicInfoJsonObject.getDouble("merid"));
            goodsInfoBasicInfo.setName(basicInfoJsonObject.getString("name"));
            goodsInfoBasicInfo.setOrder_process_rules(basicInfoJsonObject.getDouble("order_process_rules"));
            goodsInfoBasicInfo.setBook_rules(basicInfoJsonObject.getDouble("book_rules"));
            goodsInfoBasicInfo.setIs_refund_rules(basicInfoJsonObject.getDouble("is_refund_rules"));
            goodsInfoBasicInfo.setRefund_rules_hour(basicInfoJsonObject.getDouble("refund_rules_hour"));
            goodsInfoBasicInfo.setRefund_rules(basicInfoJsonObject.getString("refund_rules"));
            goodsInfoBasicInfo.setStatus(basicInfoJsonObject.getDouble("status"));
            goodsInfoBasicInfo.setStaff_id(basicInfoJsonObject.getDouble("staff_id"));
            goodsInfoBasicInfo.setUpdate_time(basicInfoJsonObject.getString("update_time"));
            goodsInfoBasicInfo.setCreate_time(basicInfoJsonObject.getString("create_time"));

            goodsInfo.setGoodsInfoBasicInfo(goodsInfoBasicInfo);

            JSONArray priceRulesJSONArray = dataJsonObject.getJSONArray("priceRules");
            int length = priceRulesJSONArray.length();
            List<GoodsInfoPriceRule> priceRules = new ArrayList<GoodsInfoPriceRule>();

            for (int i = 0; i < length; i++) {
                JSONObject priceRuleJSONObject = priceRulesJSONArray.getJSONObject(i);

                GoodsInfoPriceRule goodsInfoPriceRule = new GoodsInfoPriceRule();
                goodsInfoPriceRule.setMer_price_id(priceRuleJSONObject.getString("mer_price_id"));
                goodsInfoPriceRule.setMerid(priceRuleJSONObject.getString("merid"));
                goodsInfoPriceRule.setName(priceRuleJSONObject.getString("name"));
                goodsInfoPriceRule.setPrice_id(priceRuleJSONObject.getString("price_id"));
                goodsInfoPriceRule.setOperate(priceRuleJSONObject.getString("operate"));
                goodsInfoPriceRule.setNumber(priceRuleJSONObject.getString("number"));
                goodsInfoPriceRule.setUnit(priceRuleJSONObject.getString("unit"));

                priceRules.add(goodsInfoPriceRule);
            }
            goodsInfo.setPriceRules(priceRules);

            JSONArray itemsJSONArray = dataJsonObject.getJSONArray("items");
            length = itemsJSONArray.length();
            List<GoogDetail> googDetails = new ArrayList<GoogDetail>();

            for (int i = 0; i < length; i++) {
                JSONObject priceRuleJSONObject = itemsJSONArray.getJSONObject(i);
                GoogDetail googDetail = new GoogDetail();
                HttpResultUtil.item2GoodDetail(googDetail, priceRuleJSONObject);
                googDetails.add(googDetail);
            }
            goodsInfo.setGoogDetails(googDetails);

            resultBean.setObject(goodsInfo);
        }
        return resultBean;
    }


    /**
     * @param mer_item_id 商品明细ID
     *                    number 日期数量 否(默认7)
     * @return
     */
    public ResultBean showBookDays(String mer_item_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_item_id", mer_item_id);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ITEM_SHOW_BOOK_DAYS, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJsonObject = new JSONObject(resultBean.getData());
            List<BookDay> bookDays = new ArrayList<BookDay>();

            DateTime todayDateTime = new DateTime();
            String today = todayDateTime.toString("yyyy-MM-dd");
            for (String key : dataJsonObject.keySet()) {
                BookDay bookDay = new BookDay();
                String day = dataJsonObject.getJSONObject(key).getString("day");
                bookDay.setDay(day);
                bookDay.setStatus(dataJsonObject.getJSONObject(key).getInt("status"));
                DateTime dateTime = new DateTime(day);
                if (today.equals(day)) {
                    bookDay.setWeek("今日");
                } else {
                    bookDay.setWeek(dateTime.toString("EE", Locale.CHINESE));
                }
                bookDay.setFormatDay(dateTime.toString("MM月dd日"));
                bookDays.add(bookDay);
            }

            Ordering<BookDay> ordering = new Ordering<BookDay>() {
                public int compare(BookDay left, BookDay right) {
                    return left.getDay().compareTo(right.getDay());
                }
            };
            bookDays = ordering.sortedCopy(bookDays);

            resultBean.setLists(bookDays);
            resultBean.setData(StringUtils.EMPTY);
        }
        return resultBean;
    }

    /**
     * 时段型获取报价时段
     *
     * @param mer_item_id  商品明细ID
     * @param card_type_id 卡种ID
     * @param date         日期
     * @return
     * @throws Exception
     */
    public ResultBean getValidPriceTime(String mer_item_id, String card_type_id, String date) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_item_id", mer_item_id);
        jsonObject.put("card_type_id", card_type_id);
        jsonObject.put("date", date);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + MER_VALID_PRICE_TIME, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJsonObject = new JSONObject(resultBean.getData());
            List<BookDay> bookDays = new ArrayList<BookDay>();
            for (String key : dataJsonObject.keySet()) {
                BookDay bookDay = new BookDay();
                bookDay.setDay(dataJsonObject.getJSONObject(key).getString("day"));
                bookDay.setStatus(dataJsonObject.getJSONObject(key).getInt("status"));
                bookDays.add(bookDay);
            }
            resultBean.setLists(bookDays);
        }
        return resultBean;

    }


    /**
     * 获取商品明细的基本信息(商品、商品明细、场地、场馆)
     *
     * @param mer_item_id 商品明细ID
     * @return
     */
    public String itemGetMerchandiseItemInfo(String mer_item_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_item_id", mer_item_id);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ITEM_GET_MERCHANDISE_DETAIL, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        return result;
    }


    /**
     * ?mer_id=1020457&
     * latitude=39.929985&longitude=116.395645&city=52&sport=life&show_price_id=1845&default_price_id=1845&radius=10000&price_sort=&district=510
     *
     * @return
     */
    public ResultBean lists(String mer_id, String latitude, String longitude, String city, String sport, String show_price_id,
                            String default_price_id, String radius, String price_sort, String district, int page,
                            int pagesize) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_id", mer_id);
        jsonObject.put("latitude", latitude);
        jsonObject.put("longitude", longitude);
        jsonObject.put("city", city);
        jsonObject.put("sport", sport);
        jsonObject.put("show_price_id", show_price_id);
        jsonObject.put("default_price_id", default_price_id);
        jsonObject.put("radius", radius);
        jsonObject.put("price_sort", price_sort);
        jsonObject.put("district", district);

        String result = HttpUtil.doPost(AccessServices.PLATFORM_SERVICE_URL + LISTS, jsonObject.toString(), AccessServices.PLATFORM_SERVICE_KEY);
        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {

        }
        return resultBean;
    }


    /**
     * 获取八天价格汇总以及库存汇总(乒羽篮网）
     *
     * @param mer_item_id
     * @param mer_price_id
     * @return
     */
    public ResultBean priceAndInventorySummaryCommon(String mer_item_id, String mer_price_id, String cid) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_item_id", mer_item_id);
        jsonObject.put("mer_price_id", mer_price_id);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ITEM_PRICE_INVENTORY_SUMMARY_COMMON, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<PriceAndInventorySummaryCommon> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJSONObject = new JSONObject(resultBean.getData());
            List<PriceAndInventorySummaryCommon> priceAndInventorySummaryCommons = new ArrayList<PriceAndInventorySummaryCommon>();
            for (String key : dataJSONObject.keySet()) {
                JSONObject json = dataJSONObject.getJSONObject(key);
                PriceAndInventorySummaryCommon priceAndInventorySummaryCommon = new PriceAndInventorySummaryCommon();

                priceAndInventorySummaryCommon.setInventory_summaray(json.getInt("inventory_summaray"));
                DateTime dateTime = null;
                if (json.has("price_summaray") && json.optJSONObject("price_summaray") != null) {
                    JSONObject priceSummarayJSONObject = json.getJSONObject("price_summaray");
                    PriceSummaray priceSummaray = null;
                    try {
                        priceSummaray = objectMapper.readValue(priceSummarayJSONObject.toString(), PriceSummaray.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //日期处理
               /*     DateTime todayDateTime = new DateTime();
                    String today = todayDateTime.toString("yyyy-MM-dd");
                    dateTime = new DateTime(priceSummaray.getDay());
                    if (today.equals(priceSummaray.getDay())) {
                        priceAndInventorySummaryCommon.setWeek("今日");
                    } else {
                        priceAndInventorySummaryCommon.setWeek(dateTime.toString("EE", Locale.CHINESE));
                    }
                    priceAndInventorySummaryCommon.setFormatDay(dateTime.toString("MM月dd日"));*/
                    priceAndInventorySummaryCommon.setPriceSummaray(priceSummaray);
                } else{
                    //未配置价格异常处理
                    PriceSummaray priceSummaray = new PriceSummaray();
                    priceSummaray.setMin_price("0.00");
                    priceSummaray.setMax_price("0.00");
                    priceSummaray.setDay(key);
                    priceAndInventorySummaryCommon.setPriceSummaray(priceSummaray);
                }
                //日期处理
                DateTime todayDateTime = new DateTime();
                String today = todayDateTime.toString("yyyy-MM-dd");
                dateTime = new DateTime(key);
                if (today.equals(key)) {
                    priceAndInventorySummaryCommon.setWeek("今日");
                } else {
                    priceAndInventorySummaryCommon.setWeek(dateTime.toString("EE", Locale.CHINESE));
                }
                priceAndInventorySummaryCommon.setFormatDay(dateTime.toString("MM月dd日"));

                priceAndInventorySummaryCommon.setMer_item_id(mer_item_id);
                priceAndInventorySummaryCommon.setMer_price_id(mer_price_id);
                priceAndInventorySummaryCommon.setCid(cid);
                priceAndInventorySummaryCommons.add(priceAndInventorySummaryCommon);
            }

            //排序
            Ordering<PriceAndInventorySummaryCommon> ordering = new Ordering<PriceAndInventorySummaryCommon>() {
                public int compare(PriceAndInventorySummaryCommon left, PriceAndInventorySummaryCommon right) {
                  /*  if (left.getPriceSummaray() != null && right.getPriceSummaray() != null) {
                        return left.getPriceSummaray().getDay().compareTo(right.getPriceSummaray().getDay());
                    }
                    return 0;*/
                    return left.getFormatDay().compareTo(right.getFormatDay());
                }
            };
            priceAndInventorySummaryCommons = ordering.sortedCopy(priceAndInventorySummaryCommons);

            resultBean.setLists(priceAndInventorySummaryCommons);
            resultBean.setData(StringUtils.EMPTY);
        }
        return resultBean;
    }


    /**
     * 获取一天商品明细的价格和库存信息（日期、时段、小时）
     *
     * @param mer_item_id
     * @param mer_price_id
     * @param date
     * @return
     */
    public ResultBean<OneDayItemPrice> getOneDayItemPrice(String mer_item_id, String mer_price_id, String date) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_item_id", mer_item_id);
        jsonObject.put("mer_price_id", mer_price_id);
        jsonObject.put("date", date);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ITEM_ONE_DAY_IMEM_PRICE, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<OneDayItemPrice> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJSONObject = new JSONObject(resultBean.getData());
            OneDayItemPrice oneDayItemPrice = new OneDayItemPrice();

            if (dataJSONObject.has("inventory_info")) {
                JSONObject inventoryJSONObject = dataJSONObject.getJSONObject("inventory_info");
                List<OneDayItemPrice.InventoryInfo> inventoryInfos = new ArrayList<OneDayItemPrice.InventoryInfo>();
                for (String key : inventoryJSONObject.keySet()) {
                    OneDayItemPrice.InventoryInfo inventoryInfo = oneDayItemPrice.new InventoryInfo();
                    inventoryInfo.setDay(key);
                    inventoryInfo.setNumber(inventoryJSONObject.getInt(key));
                    inventoryInfos.add(inventoryInfo);
                }
                oneDayItemPrice.setInventoryInfos(inventoryInfos);
            }

            if (dataJSONObject.has("price_info")) {
                JSONArray priceinfoJSONArray = dataJSONObject.getJSONArray("price_info");
                List<PriceInfo> priceInfos = new ArrayList<PriceInfo>();
                int length = priceinfoJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject priceInfoJSONObject = priceinfoJSONArray.getJSONObject(i);
                    PriceInfo priceInfo = null;
                    try {
                        priceInfo = objectMapper.readValue(priceInfoJSONObject.toString(), PriceInfo.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    priceInfos.add(priceInfo);
                }
                oneDayItemPrice.setPriceInfos(priceInfos);
            }

            if (oneDayItemPrice.getInventoryInfos().get(0).getNumber() > 0
                    && !CollectionUtils.isEmpty(oneDayItemPrice.getPriceInfos())
                    && Integer.parseInt(oneDayItemPrice.getPriceInfos().get(0).getPrepay_price()) > 0) {
                oneDayItemPrice.setDestine((byte) 1);
            } else {
                oneDayItemPrice.setDestine((byte) 0);
            }

            resultBean.setObject(oneDayItemPrice);
            //置空减少数据传输
            resultBean.setData(StringUtils.EMPTY);
        }
        return resultBean;
    }

    /**
     * 获取小时型库存
     *
     * @param mer_item_id
     * @param mer_price_id
     * @param date
     * @return
     * @throws Exception
     */
    public ResultBean getOneDayMerItemPrice(String mer_item_id, String mer_price_id, String date) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_item_id", mer_item_id);
        jsonObject.put("mer_price_id", mer_price_id);
        jsonObject.put("date", date);
        String result = HttpUtil.doPost(AccessServices.PLATFORM_SERVICE_URL + MER_ONE_DAY_MER_ITEM_PRICE, jsonObject.toString(), AccessServices.PLATFORM_SERVICE_KEY);
        ResultBean<PriceAndInventorySummaryCommon> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJSONObject = new JSONObject(resultBean.getData());
            for (String key : dataJSONObject.keySet()) {

            }
        }
        return resultBean;
    }


    /**
     * 获取一天商品明细的价格和库存信息（日期、时段、小时） ------时段
     *
     * @param mer_item_id
     * @param mer_price_id
     * @param date
     * @return
     */
    public ResultBean<OneDayItemPrice> getOneDayItemPriceForTimeinterval(String mer_item_id, String mer_price_id, String date) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_item_id", mer_item_id);
        jsonObject.put("mer_price_id", mer_price_id);
        jsonObject.put("date", date);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ITEM_ONE_DAY_IMEM_PRICE, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<OneDayItemPrice> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJSONObject = new JSONObject(resultBean.getData());
            OneDayItemPrice oneDayItemPrice = new OneDayItemPrice();

            //时间片价格 key时间起点 value prepay_price
            Map<Integer, Double> timePrice = new HashMap();
            Map<Integer, PriceInfo> timePriceInfo = new HashMap();

            //价格解析  >>>>>>> 填充timePrice>>>>>>填充Hour的price>>>> 判断是否可预订(有价格 状态为1)
            if (dataJSONObject.has("price_info") && dataJSONObject.optJSONObject("price_info") != null) {
                JSONObject priceInfoJSONObject = dataJSONObject.getJSONObject("price_info");
                for (String key : priceInfoJSONObject.keySet()) {
                    JSONObject priceinfo = priceInfoJSONObject.getJSONObject(key);
                    timePrice.put(priceinfo.getInt("start_hour"), priceinfo.getDouble("prepay_price"));

                    PriceInfo priceInfo = null;
                    try {
                        priceInfo = objectMapper.readValue(priceinfo.toString(), PriceInfo.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    timePriceInfo.put(priceinfo.getInt("start_hour"), priceInfo);
                }
            } else if (dataJSONObject.has("price_info") && dataJSONObject.optJSONArray("price_info") != null) {
                JSONArray priceInfoJSONObject = dataJSONObject.getJSONArray("price_info");
                for (int i = 0; i < priceInfoJSONObject.length(); i++) {
                    JSONObject priceinfo = priceInfoJSONObject.getJSONObject(i);
                    timePrice.put(priceinfo.getInt("start_hour"), priceinfo.getDouble("prepay_price"));
                    PriceInfo priceInfo = null;
                    try {
                        priceInfo = objectMapper.readValue(priceinfo.toString(), PriceInfo.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    timePriceInfo.put(priceinfo.getInt("start_hour"), priceInfo);
                }
            }

            //库存解析
            if (dataJSONObject.has("inventory_info")) {
                JSONObject inventoryJSONObject = dataJSONObject.getJSONObject("inventory_info");
                List<OneDayItemPrice.InventoryInfo> inventoryInfos = new ArrayList<OneDayItemPrice.InventoryInfo>();

                for (String key : inventoryJSONObject.keySet()) {
                    JSONObject inventoryInfoJson = inventoryJSONObject.getJSONObject(key);
                    OneDayItemPrice.InventoryInfo inventoryInfo = oneDayItemPrice.new InventoryInfo();

                    String piece_id = inventoryInfoJson.getString("piece_id");

                    inventoryInfo.setPiece_id(piece_id);
                    inventoryInfo.setVenue_id(inventoryInfoJson.getString("venue_id"));
                    inventoryInfo.setName(inventoryInfoJson.getString("name"));
                    inventoryInfo.setStatus(inventoryInfoJson.getString("status"));


                    List<OneDayItemPrice.HourInfo> hours = new ArrayList<OneDayItemPrice.HourInfo>();
                    if (inventoryInfoJson.has("hour") && inventoryInfoJson.optJSONObject("hour") != null) {
                        JSONObject hourJSONObject = inventoryInfoJson.getJSONObject("hour");

                        //解析每个小时单元格的 状态是否可预订 以及赋予价格信息
                        for (String houorKey : hourJSONObject.keySet()) {
                            JSONObject hourJSON = hourJSONObject.getJSONObject(houorKey);
                            OneDayItemPrice.HourInfo hourInfo = oneDayItemPrice.new HourInfo();

                            hourInfo.setPiece_id(piece_id);
                            Integer hour = hourJSON.getInt("hour");
                            hourInfo.setHour(hour);
                            int status = hourJSON.getInt("status");
                            hourInfo.setStatus(status);
                            double price = null == timePrice.get(hour) ? 0 : timePrice.get(hour);
                            hourInfo.setPrepay_price(price);
                            if (1 == status && price > 2) {
                                hourInfo.setCanbook(1);
                            } else {
                                hourInfo.setCanbook(0);
                            }
                            PriceInfo priceInfo = timePriceInfo.get(hour);
                            hourInfo.setPriceInfo(priceInfo);

                            hours.add(hourInfo);
                        }
                    } else if (inventoryInfoJson.has("hour") && inventoryInfoJson.optJSONArray("hour") != null) {
                        JSONArray hourJSONObject = inventoryInfoJson.getJSONArray("hour");
                        //解析每个小时单元格的 状态是否可预订 以及赋予价格信息
                        int length = hourJSONObject.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject hourJSON = hourJSONObject.getJSONObject(i);
                            OneDayItemPrice.HourInfo hourInfo = oneDayItemPrice.new HourInfo();

                            hourInfo.setPiece_id(piece_id);
                            Integer hour = hourJSON.getInt("hour");
                            hourInfo.setHour(hour);
                            int status = hourJSON.getInt("status");
                            hourInfo.setStatus(status);
                            double price = null == timePrice.get(hour) ? 0 : timePrice.get(hour);
                            hourInfo.setPrepay_price(price);
                            if (1 == status && price > 2) {
                                hourInfo.setCanbook(1);
                            } else {
                                hourInfo.setCanbook(0);
                            }
                            PriceInfo priceInfo = timePriceInfo.get(hour);
                            hourInfo.setPriceInfo(priceInfo);

                            hours.add(hourInfo);
                        }
                    }
                    inventoryInfo.setHourInfos(hours);
                    inventoryInfos.add(inventoryInfo);
                }
                oneDayItemPrice.setInventoryInfos(inventoryInfos);
            }
            resultBean.setObject(oneDayItemPrice);
            //置空减少数据传输
            resultBean.setData(StringUtils.EMPTY);
        }
        return resultBean;
    }


    /**
     * 根据商品ID及卡种ID获取默认价格ID
     *
     * @param merid
     * @param card_type_id
     * @return
     */
    public ResultBean getMerPriceId(String merid, String card_type_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merid", merid);
        jsonObject.put("card_type_id", card_type_id);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + PROJECT_MER_PRICE_ID, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean resultBean = HttpResultUtil.result2Bean(result);
        return resultBean;
    }


}
