package com.saidian.web.order;

import com.google.common.base.Strings;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.User;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createOrder(String mer_item_id, String book_day, Integer cid, ModelMap modelMap, HttpSession httpSession, HttpServletRequest httpServletRequest) throws Exception {

        //判断是否登录
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            modelMap.addAttribute("back_url", httpServletRequest.getRequestURI() + (Strings.isNullOrEmpty(httpServletRequest.getQueryString()) ? "" : "?" + httpServletRequest.getQueryString()));
            return "login/index";
        }

        if (null == cid)
            throw new Exception("运动类型不能为空");

        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String telephone = jsonObject.getString("telephone");
        modelMap.addAttribute("telephone", telephone);

        String mer_id = HttpParams.merid;

        //获取订单信息
        String detailResultBean = bTiemService.itemGetMerchandiseItemInfo(mer_item_id);
        JSONObject dataJsonObject = new JSONObject(detailResultBean);
        dataJsonObject = new JSONObject(dataJsonObject.getString("data"));

        modelMap.addAttribute("detail", dataJsonObject);

        DateTime dateTime = new DateTime(book_day);
        modelMap.addAttribute("day", dateTime.toString("MM月dd日") + " " + dateTime.toString("EE", Locale.CHINESE));

        switch (cid) {
            //日期型
            case 108:
            case 109:
            case 122:
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


}
