package com.saidian.web.order;

import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.siteinfo.OneDayItemPrice;
import com.saidian.web.bean.siteinfo.PriceInfo;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String createOrder(String mer_item_id, String mer_price_id, String book_day, Integer cid, ModelMap modelMap, HttpSession httpSession, HttpServletRequest httpServletRequest) throws Exception {

        System.out.println("----------------------------------------------");
        System.out.println("mer_item_id = [" + mer_item_id + "], book_day = [" + book_day + "], cid = [" + cid + "], " +
                "modelMap = [" + modelMap + "], httpSession = [" + httpSession + "], httpServletRequest = [" + httpServletRequest + "]");
        System.out.println("----------------------------------------------");

        //判断是否登录 todo 待恢复
      /*  User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            modelMap.addAttribute("back_url", httpServletRequest.getRequestURI() + (Strings.isNullOrEmpty(httpServletRequest.getQueryString()) ? "" : "?" + httpServletRequest.getQueryString()));
            return "login/index";
        }*/

        if (null == cid)
            throw new Exception("运动类型不能为空");

      /*  JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String telephone = jsonObject.getString("telephone");
        modelMap.addAttribute("telephone", telephone);*/

        String mer_id = HttpParams.merid;

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

        switch (cid) {
            //日期型
            case 108:
            case 109:
            case 122:
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
                return "order/createtimeorder";
        }

        //TODO 创建订单失败
        return "";
    }

    @RequestMapping(value = "submitOrder")
    @ResponseBody
    public Object submitOrder(Integer card_id, String account_no,
                              Integer cid, String mer_item_id, String mer_price_id, String book_day, String other_money_name, String other_money,
                              String book_phone, String note, String create_staff_id,
                              String order_money

            , HttpSession httpSession) throws Exception {

        /*JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
         uid = jsonObject.getString("uid");
*/
        String uid = "0h1170114160444ihv";

        JSONArray items = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mer_price_id", mer_price_id);
        jsonObject.put("order_money", order_money);
        jsonObject.put("reduce_money", 0);
        jsonObject.put("pay_money", order_money);

        items.put(jsonObject);


        switch (cid) {
            //日期型
            case 108:
            case 109:

                //todo card_id
            /*    ResultBean resultBean = createOrderService.createSwimOrder(HttpParams.serviceId,
                        HttpParams.ORDER_SOURCE, uid, Integer.parseInt(HttpParams.cardId), 0, "",
                        cid.toString(), mer_item_id, book_day, "", "0", book_phone,
                        "", "", items.toString(), "1", order_money, 0, order_money);
*/
              /*  ResultBean resultBean = createOrderService.createSwimOrder(HttpParams.serviceId,
                        HttpParams.ORDER_SOURCE, uid,15, 0, "",
                        cid.toString(), mer_item_id, book_day, "", "0", book_phone,
                        "", "", items.toString(), "1", order_money, 0, order_money);


                System.out.println(resultBean.toString());
*/
            case 122:

                // return "order/createdayorder";

            case 101:
            case 102:
            case 104:
            case 106:

                //return "order/createtimeorder";
        }
        return null;
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
                "", items.toString(), "", "5600", 0, "5600");

        //   return orderService.orderLists("","77v","","0h1170105151026m47",1,20);

        //  return orderService.orderCancel("", "0h1170105151026m47");

        //    return orderService.orderUnsubscribe("", "0h1170105151026m47","");


        return orderService.orderDetail("", "0h1170105151026m47");

    }

}
