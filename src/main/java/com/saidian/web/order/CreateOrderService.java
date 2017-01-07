package com.saidian.web.order;

import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import com.saidian.web.bean.BookDay;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/6.
 */
@Service
public class CreateOrderService {

    private static String CREATE_FITNESS_ORDER = "order/createFitnessOrder";

    public ResultBean createFitnessOrder(String service_id, int source, String uid, int card_type_id, int card_id, String account_no,
                                         String cid, String mer_item_id, String book_day, String other_money_name, String other_money, String book_phone,
                                         String note, String create_staff_id, String items, String is_sendsms, String order_money, int reduce_money, String pay_money) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service_id", service_id);
        jsonObject.put("source", source);
        jsonObject.put("uid", uid);
        jsonObject.put("card_type_id", card_type_id);
        jsonObject.put("card_id", card_id);
        jsonObject.put("account_no", account_no);
        jsonObject.put("cid", cid);
        jsonObject.put("mer_item_id", mer_item_id);
        jsonObject.put("book_day", book_day);
        jsonObject.put("other_money_name", other_money_name);
        jsonObject.put("other_money", other_money);
        jsonObject.put("book_phone", book_phone);
        jsonObject.put("note", note);
        jsonObject.put("create_staff_id", create_staff_id);
        jsonObject.put("items", items);
        jsonObject.put("is_sendsms", is_sendsms);
        jsonObject.put("order_money", order_money);
        jsonObject.put("reduce_money", reduce_money);
        jsonObject.put("pay_money", pay_money);

      //  JSONObject data = new JSONObject("{\"service_id\":\"77v\",\"source\":7,\"uid\":\"0h1170105151026m47\",\"card_type_id\":15,\"card_id\":0,\"account_no\":\"\",\"cid\":\"108\",\"mer_item_id\":\"10200031000009\",\"book_day\":\"2017-01-08\",\"order_money\":5600,\"reduce_money\":0,\"other_money_name\":\"\",\"other_money\":0,\"pay_money\":5600,\"book_phone\":\"18210842622\",\"note\":\"\",\"create_staff_id\":0,\"items\":[{\"play_time\":\"22:00\",\"play_person_name\":\"\",\"start_hour\":\"22\",\"end_hour\":\"24\",\"piece_id\":\"\",\"mer_price_id\":\"1626\",\"order_money\":\"5600\",\"reduce_money\":0,\"pay_money\":\"5600\",\"is_rights\":0}]}");

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + CREATE_FITNESS_ORDER, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        System.out.println("--------------------------------------------------------------");
        System.out.println(result);
        System.out.println("--------------------------------------------------------------");

        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {

        }

        return null;
    }


}
