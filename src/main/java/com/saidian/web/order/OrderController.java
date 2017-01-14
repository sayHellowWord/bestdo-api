package com.saidian.web.order;

import com.saidian.config.HttpParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/1/5.
 */
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CreateOrderService createOrderService;

    @ResponseBody
    @RequestMapping(value = "createOrder")
    public String createOrder(String mer_item_id, String book_day, Integer cid) throws Exception {
        String mer_id = HttpParams.merid;
        System.out.println("mer_item_id = [" + mer_item_id + "], book_day = [" + book_day + "], cid = [" + cid + "]");
        if (null == cid)
            throw new Exception("运动类型不能为空");

       /* switch (cid) {
            //日期型
            case 108:
            case 109:
            case 122:
                return "";



        }
*/
       return null;
    }

}
