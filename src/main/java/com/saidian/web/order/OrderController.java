package com.saidian.web.order;

import com.saidian.bean.ResultBean;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/1/5.
 */
@RequestMapping(value = "order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CreateOrderService createOrderService;

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

        ResultBean resultBean =  createOrderService.createFitnessOrder("77v",7,"0h1170105151026m47",15,0,"",
                "107","10200031000009","2017-01-08","","0","15810045436","",
                "",items.toString(),"","5600",0,"5600");

        //   return orderService.orderLists("","77v","","0h1170105151026m47",1,20);

        //  return orderService.orderCancel("", "0h1170105151026m47");

        //    return orderService.orderUnsubscribe("", "0h1170105151026m47","");


        return orderService.orderDetail("", "0h1170105151026m47");

    }

}
