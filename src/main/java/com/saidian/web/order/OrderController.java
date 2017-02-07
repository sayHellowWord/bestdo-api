package com.saidian.web.order;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableBiMap;
import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.TimeTable;
import com.saidian.web.bean.User;
import com.saidian.web.bean.order.Order;
import com.saidian.web.bean.siteinfo.OneDayItemPrice;
import com.saidian.web.bean.siteinfo.PriceInfo;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/1/5.
 */
@RequestMapping(value = "order")
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CreateOrderService createOrderService;

    @Autowired
    BTiemService bTiemService;//场馆

    @RequestMapping(value = "createOrder")
    public String createOrder(String mer_item_id, String mer_price_id, String book_day, Integer cid
            , String timeStr, String totalMoney
            , ModelMap modelMap, HttpSession httpSession, HttpServletRequest httpServletRequest) throws Exception {
        System.out.println("----------------------------------------------");
        System.out.println("mer_item_id = [" + mer_item_id + "], mer_price_id = [" + mer_price_id + "], book_day = [" + book_day + "], " +
                "cid = [" + cid + "], timeStr = [" + timeStr + "], modelMap = [" + modelMap + "], httpSession = [" + httpSession + "], httpServletRequest = [" + httpServletRequest + "]");
        System.out.println("----------------------------------------------");
        //判断是否登录 todo 待恢复
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            modelMap.addAttribute("back_url", httpServletRequest.getRequestURI() + (Strings.isNullOrEmpty(httpServletRequest.getQueryString()) ? "" : "?" + httpServletRequest.getQueryString()));
            return "login/index";
        }
        if (null == cid)
            throw new Exception("运动类型不能为空");
        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        if (null != jsonObject && jsonObject.has("telephone")) {
            String telephone = jsonObject.getString("telephone");
            modelMap.addAttribute("telephone", telephone);
        }

        //获取订单信息
        String detailResultBean = bTiemService.itemGetMerchandiseItemInfo(mer_item_id);
        JSONObject dataJsonObject = new JSONObject(detailResultBean);
        dataJsonObject = new JSONObject(dataJsonObject.getString("data"));

        modelMap.addAttribute("detail", dataJsonObject);

        DateTime dateTime = new DateTime(book_day);

        modelMap.addAttribute("day", dateTime.toString("MM月dd日") + " " + dateTime.toString("EE", Locale.CHINESE));
        modelMap.addAttribute("mer_item_id", mer_item_id);
        modelMap.addAttribute("mer_price_id", mer_price_id);
        modelMap.addAttribute("cid", cid);
        modelMap.addAttribute("book_day", book_day);

        //小时型
        if (!Strings.isNullOrEmpty(timeStr)) {
            modelMap.addAttribute("totalMoney", totalMoney);
            modelMap.addAttribute("timeStr", timeStr);
            String[] timeArr = timeStr.split(";");
            List<TimeTable> timeTables = new ArrayList<TimeTable>();
            for (String timeArrbean : timeArr) {
                String[] timeArrbeanArr = timeArrbean.split(",");
                TimeTable timeTable = new TimeTable();
                timeTable.setMoney(timeArrbeanArr[0]);
                timeTable.setPiece_id(timeArrbeanArr[1]);
                timeTable.setStart_hour(timeArrbeanArr[2]);
                timeTable.setEnd_hour(timeArrbeanArr[3]);
                timeTables.add(timeTable);
            }
            modelMap.addAttribute("timeArr", timeTables);
        }

        switch (cid) {
            //日期型
            case 108:
            case 109:
            case 113:
                OneDayItemPrice oneDayItemPrice = bTiemService.getOneDayItemPrice(mer_item_id, mer_price_id, book_day).getObject();
                //库存
                OneDayItemPrice.InventoryInfo inventoryInfo = oneDayItemPrice.getInventoryInfos().get(0);
                modelMap.addAttribute("inventoryInfo", inventoryInfo);

                //单价
                PriceInfo priceInfo = oneDayItemPrice.getPriceInfos().get(0);

                modelMap.addAttribute("priceInfo", priceInfo);
                modelMap.addAttribute("oneDayItemPrice", oneDayItemPrice);
                return "order/createdayorder";
            case 101:
            case 102:
            case 104:
            case 106:
            case 120:
                return "order/createtimeorder";
        }
        //TODO 创建订单失败
        return "";
    }

    @RequestMapping(value = "submitOrder")
    @ResponseBody
    public Object submitOrder(Integer card_id, String account_no,
                              Integer cid, String mer_item_id, String mer_price_id, String book_day, String other_money_name, String other_money,
                              String book_phone, String note, String create_staff_id, String order_money,

                              Integer start_hour, Integer end_hour, String play_time
            , String timeStr
            , HttpSession httpSession) throws Exception {

        JSONObject userObject = (JSONObject) httpSession.getAttribute("userinfo");
        String uid = userObject.getString("uid");

        card_id = Integer.parseInt((String) httpSession.getAttribute("cardid"));

        //魏继鹏测试环境数据 todo
        //  String uid = "522160521120318tRS";
        //武波
        //  String uid = "0h1170114160444ihv";

        JSONArray items = new JSONArray();
        ResultBean resultBean = null;
        JSONObject jsonObject = null;
        JSONObject jsonR = null; //结果解析
        switch (cid) {
            //日期型
            case 108://健身
                jsonObject = new JSONObject();
                jsonObject.put("mer_price_id", Integer.parseInt(mer_price_id));
                jsonObject.put("order_money", Integer.parseInt(order_money));
                jsonObject.put("reduce_money", 0);
                jsonObject.put("is_rights", 0);
                jsonObject.put("pay_money", Integer.parseInt(order_money));
                jsonObject.put("start_hour", start_hour);
                jsonObject.put("end_hour", end_hour);
                if (!Strings.isNullOrEmpty(play_time))
                    jsonObject.put("play_time", play_time);
                items.put(jsonObject);

                resultBean = createOrderService.createFitnessOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "",
                        items, "1", order_money, 0, order_money);
                dayResult(resultBean);
                break;
            case 109://游泳
                //组织订单数据
                jsonObject = new JSONObject();
                jsonObject.put("mer_price_id", Integer.parseInt(mer_price_id));
                jsonObject.put("order_money", Integer.parseInt(order_money));
                jsonObject.put("reduce_money", 0);
                jsonObject.put("is_rights", 0);
                jsonObject.put("pay_money", Integer.parseInt(order_money));
                jsonObject.put("start_hour", start_hour);
                jsonObject.put("end_hour", end_hour);
                if (!Strings.isNullOrEmpty(play_time))
                    jsonObject.put("play_time", play_time);
                items.put(jsonObject);

                resultBean = createOrderService.createSwimOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "",
                        items, "1", order_money, 0, order_money);
                dayResult(resultBean);
                break;
            case 113://台球
                jsonObject = new JSONObject();
                jsonObject.put("mer_price_id", Integer.parseInt(mer_price_id));
                jsonObject.put("order_money", Integer.parseInt(order_money));
                jsonObject.put("reduce_money", 0);
                jsonObject.put("is_rights", 0);
                jsonObject.put("pay_money", Integer.parseInt(order_money));
                jsonObject.put("start_hour", start_hour);
                jsonObject.put("end_hour", end_hour);
                if (!Strings.isNullOrEmpty(play_time))
                    jsonObject.put("play_time", play_time);
                items.put(jsonObject);

                resultBean = createOrderService.createMixOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "",
                        items, "1", order_money, 0, order_money);
                dayResult(resultBean);
                break;
            case 101: //网球订单
                //小时型
                timeParams(mer_price_id, play_time, timeStr, items);
                resultBean = createOrderService.createTennisOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "", items,
                        "1", order_money, 0, order_money);
                dayResult(resultBean);
                break;
            case 120://足球
                timeParams(mer_price_id, play_time, timeStr, items);
                resultBean = createOrderService.createFootballOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "", items,
                        "1", order_money, 0, order_money);

                jsonR = new JSONObject(resultBean.getData());
                resultBean.setObject(ImmutableBiMap.of("oid", jsonR.getString("oid"), "mer_item_id", jsonR.getString("mer_item_id")
                        , "pay_money", jsonR.getString("pay_money")));
                break;
            case 102://羽毛球
                timeParams(mer_price_id, play_time, timeStr, items);
                resultBean = createOrderService.createBadmintonOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "", items,
                        "1", order_money, 0, order_money);

                jsonR = new JSONObject(resultBean.getData());
                resultBean.setObject(ImmutableBiMap.of("oid", jsonR.getString("oid"), "mer_item_id", jsonR.getString("mer_item_id")
                        , "pay_money", jsonR.getString("pay_money")));
                break;
            case 104://篮球
                timeParams(mer_price_id, play_time, timeStr, items);
                resultBean = createOrderService.createBasketballOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "", items,
                        "1", order_money, 0, order_money);
                jsonR = new JSONObject(resultBean.getData());
                resultBean.setObject(ImmutableBiMap.of("oid", jsonR.getString("oid"), "mer_item_id", jsonR.getString("mer_item_id")
                        , "pay_money", jsonR.getString("pay_money")));
                break;
            case 106://乒乓球
                timeParams(mer_price_id, play_time, timeStr, items);
                resultBean = createOrderService.createTableTennisOrder(HttpParams.serviceId, HttpParams.ORDER_SOURCE, uid,
                        card_id, 0, "", cid.toString(), mer_item_id, book_day,
                        "", "0", book_phone, "", "", items,
                        "1", order_money, 0, order_money);
                jsonR = new JSONObject(resultBean.getData());
                resultBean.setObject(ImmutableBiMap.of("oid", jsonR.getString("oid"), "mer_item_id", jsonR.getString("mer_item_id")
                        , "pay_money", jsonR.getString("pay_money")));
                break;
        }
        return resultBean;
    }


    @RequestMapping(value = "/orderLists")
    public String orderLists(String status, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("status", status);
        return "/order/list";
    }


    @RequestMapping(value = "/orderListsSearch")
    @ResponseBody
    public ResultBean orderListsSearch(String status, String cid, Integer page, Integer pagesize, HttpSession httpSession) throws Exception {
        //todo udi
        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String uid = jsonObject.getString("uid");

        // return orderService.orderLists(status, HttpParams.project_no, cid, "0h1170114160444ihv", null == page ? 1 : page, null == pagesize ? 10 : pagesize);
        return orderService.orderLists(status, HttpParams.project_no, cid, uid, null == page ? 1 : page, null == pagesize ? 10 : pagesize);
    }

    @RequestMapping(value = "detail")
    public String orderDeail(String oid, ModelMap modelMap, HttpSession httpSession) throws Exception {
        //todo udi
        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String uid = jsonObject.getString("uid");

        Order order = orderService.orderDetail(oid, uid).getObject();
        // Order order = orderService.orderDetail(oid, "0h1170114160444ihv").getObject();
        if ("0".equals(order.getStatus())) {//未支付计算倒计时时间 总时长为15分钟
            String create_time = order.getCreate_time();
            DateTime createTimeDateTime = new DateTime(Long.parseLong(create_time) * 1000);
            DateTime currentTimeDateTime = new DateTime();
            int diffMinutes = Minutes.minutesBetween(createTimeDateTime, currentTimeDateTime).getMinutes() % 60;
            int diffSeconds = Seconds.secondsBetween(createTimeDateTime, currentTimeDateTime).getSeconds() % 60;
            if (diffMinutes < 15 || (diffMinutes == 14 && diffSeconds < 30)) {//小于30秒 才让倒计时不然太快没意义（减少网络传输误差）
                modelMap.addAttribute("canRepay", 1);
                modelMap.addAttribute("diffMinutes", 14- diffMinutes);
                /*modelMap.addAttribute("diffMinutes", 5 - diffMinutes);*/
                modelMap.addAttribute("diffSeconds", 60 - diffSeconds);
            } else {
                modelMap.addAttribute("canRepay", 0);
            }
        }
        modelMap.addAttribute("order", order);
        return "/order/detail";
    }

    @RequestMapping(value = "searchDeail")
    @ResponseBody
    public ResultBean searchDeail(String oid, HttpSession httpSession) throws Exception {
        //todo udi
        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String uid = jsonObject.getString("uid");

        return orderService.orderDetail(oid, uid);
        //  return orderService.orderDetail(oid, "0h1170114160444ihv");
    }

    @RequestMapping(value = "paytimeout")
    public String paySucPage(String order_id, ModelMap modelMap) {
        System.out.println("========    suc     ====" + order_id);
        modelMap.addAttribute("order_id", order_id);
        return "/order/paytimeout";
    }

    @RequestMapping(value = "unsubscribe")
    public String unsubscribe(String order_id, ModelMap modelMap) {
        modelMap.addAttribute("order_id", order_id);
        return "/order/unsubscribe";
    }


    @RequestMapping(value = "submitunsubscribe")
    @ResponseBody
    public ResultBean orderUnsubscribe(String order_id, String reason, HttpSession httpSession) throws Exception {
        //todo udi
        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String uid = jsonObject.getString("uid");

        // ResultBean resultBean = orderService.orderUnsubscribe(order_id, "0h1170114160444ihv", reason);
        ResultBean resultBean = orderService.orderUnsubscribe(order_id, uid, reason);
        return resultBean;
    }

    @RequestMapping(value = "unsubscriberesult")
    public String unsubscribesuc(String order_id, String code, ModelMap modelMap) {
        modelMap.addAttribute("order_id", order_id);
        modelMap.addAttribute("code", code);
        return "/order/unsubscriberesult";
    }


    //日期型返回结果处理
    private void dayResult(ResultBean resultBean) {
        if (200 == resultBean.getCode()) {
            JSONObject json = new JSONObject(resultBean.getData());
            if (json.has("oid")) {
                resultBean.setObject(ImmutableBiMap.of("oid", json.getString("oid"), "mer_item_id", json.getString("mer_item_id")
                        , "pay_money", json.getString("pay_money")));
            } else {
                resultBean.setCode(-1);
                resultBean.setMsg(json.toString());
            }
        }
    }

    //组织小时型参数
    private void timeParams(String mer_price_id, String play_time, String timeStr, JSONArray items) {
        JSONObject jsonObject;
        if (!Strings.isNullOrEmpty(timeStr)) {
            String[] timeArr = timeStr.split(";");
            List<TimeTable> timeTables = new ArrayList<TimeTable>();
            for (String timeArrbean : timeArr) {
                String[] timeArrbeanArr = timeArrbean.split(",");
                jsonObject = new JSONObject();
                jsonObject.put("mer_price_id", Integer.parseInt(mer_price_id));
                jsonObject.put("reduce_money", 0);
                jsonObject.put("is_rights", 0);
                jsonObject.put("order_money", Integer.parseInt(timeArrbeanArr[0]));
                jsonObject.put("pay_money", Integer.parseInt(timeArrbeanArr[0]));
                jsonObject.put("start_hour", timeArrbeanArr[2]);
                jsonObject.put("end_hour", timeArrbeanArr[3]);
                if (!Strings.isNullOrEmpty(play_time))
                    jsonObject.put("play_time", play_time);
                jsonObject.put("piece_id", timeArrbeanArr[1]);
                items.put(jsonObject);
            }
        }
    }


    @RequestMapping(value = "ordertest")
    public void test() throws Exception {
        JSONArray items = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_price_id", "2117");
        jsonObject.put("order_money", "5600");
        jsonObject.put("reduce_money", 0);
        jsonObject.put("pay_money", "5600");
        items.put(jsonObject);
        ResultBean resultBean = createOrderService.createTennisOrder(HttpParams.serviceId, 7, "0h1170114160444ihv",
                423, 0,
                "", "101", "10205201000000", "2017-01-18", "",
                "0", "15810045436", "", "", items, "1",
                "5600", 0, "5600");
        System.out.println(resultBean);
    }


    @RequestMapping(value = "test")
    public ResultBean accountRegister() throws Exception {
        JSONArray items = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_price_id", "167");
        jsonObject.put("order_money", "5600");
        jsonObject.put("reduce_money", 0);
        jsonObject.put("pay_money", "5600");
       /* jsonObject.put("play_time", service_id);
        jsonObject.put("play_person_name", service_id);
        jsonObject.put("start_hour", service_id);
        jsonObject.put("end_hour", service_id);*/
        items.put(jsonObject);
        ResultBean resultBean = createOrderService.createFitnessOrder("77v", 7, "0h1170105151026m47",
                15, 0, "",
                "107", "10200031000009", "2017-01-08", "", "0",
                "15810045436", "",
                "", items, "", "5600", 0, "5600");
        //   return orderService.orderLists("","77v","","0h1170105151026m47",1,20);
        //  return orderService.orderCancel("", "0h1170105151026m47");
        //    return orderService.orderUnsubscribe("", "0h1170105151026m47","");
        return orderService.orderDetail("179917011055092958", "522160521120318tRS");
    }

}
