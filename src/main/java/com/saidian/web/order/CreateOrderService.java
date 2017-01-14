package com.saidian.web.order;

import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import com.saidian.web.bean.siteinfo.BookDay;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/6.
 */
@Service
public class CreateOrderService {

    //健身
    private static String CREATE_FITNESS_ORDER = "order/createFitnessOrder";

    //创建游泳订单
    private static String CREATE_SWIM_ORDER = "order/createSwimOrder";

    //创建羽毛球订单
    private static String CREATE_BADMINTON_ORDER = "order/createBadmintonOrder";

    //网球
    private static String CREATE_TENNIS_ORDER = "order/createTennisOrder";

    //足球
    private static String CREATE_FOOTBALL_ORDER = "order/createFootballOrder";

    //创建健身订单
    public ResultBean createFitnessOrder(String service_id, int source, String uid, int card_type_id, int card_id, String account_no,
                                         String cid, String mer_item_id, String book_day, String other_money_name, String other_money, String book_phone,
                                         String note, String create_staff_id, String items, String is_sendsms, String order_money, int reduce_money, String pay_money) throws Exception {
        JSONObject jsonObject = requestParam(service_id, source, uid, card_type_id, card_id, account_no, cid, mer_item_id, book_day, other_money_name, other_money, book_phone, note, create_staff_id, items, is_sendsms, order_money, reduce_money, pay_money);
        //  JSONObject data = new JSONObject("{\"service_id\":\"77v\",\"source\":7,\"uid\":\"0h1170105151026m47\",\"card_type_id\":15,\"card_id\":0,\"account_no\":\"\",\"cid\":\"108\",\"mer_item_id\":\"10200031000009\",\"book_day\":\"2017-01-08\",\"order_money\":5600,\"reduce_money\":0,\"other_money_name\":\"\",\"other_money\":0,\"pay_money\":5600,\"book_phone\":\"18210842622\",\"note\":\"\",\"create_staff_id\":0,\"items\":[{\"play_time\":\"22:00\",\"play_person_name\":\"\",\"start_hour\":\"22\",\"end_hour\":\"24\",\"piece_id\":\"\",\"mer_price_id\":\"1626\",\"order_money\":\"5600\",\"reduce_money\":0,\"pay_money\":\"5600\",\"is_rights\":0}]}");
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + CREATE_FITNESS_ORDER, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        return resultBean;
    }


    //    创建游泳订单
    public ResultBean createSwimOrder(String service_id, int source, String uid, int card_type_id, int card_id, String account_no,
                                      String cid, String mer_item_id, String book_day, String other_money_name, String other_money, String book_phone,
                                      String note, String create_staff_id, String items, String is_sendsms, String order_money, int reduce_money, String pay_money) throws Exception {
        JSONObject jsonObject = requestParam(service_id, source, uid, card_type_id, card_id, account_no, cid, mer_item_id, book_day, other_money_name, other_money, book_phone, note, create_staff_id, items, is_sendsms, order_money, reduce_money, pay_money);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + CREATE_SWIM_ORDER, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }


    //羽毛球订单
    public ResultBean createBadmintonOrder(String service_id, int source, String uid, int card_type_id, int card_id, String account_no,
                                           String cid, String mer_item_id, String book_day, String other_money_name, String other_money, String book_phone,
                                           String note, String create_staff_id, String items, String is_sendsms, String order_money, int reduce_money, String pay_money) throws Exception {
        JSONObject jsonObject = requestParam(service_id, source, uid, card_type_id, card_id, account_no, cid, mer_item_id, book_day, other_money_name, other_money, book_phone, note, create_staff_id, items, is_sendsms, order_money, reduce_money, pay_money);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + CREATE_BADMINTON_ORDER, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

    //网球订单
    public ResultBean createTennisOrder(String service_id, int source, String uid, int card_type_id, int card_id, String account_no,
                                           String cid, String mer_item_id, String book_day, String other_money_name, String other_money, String book_phone,
                                           String note, String create_staff_id, String items, String is_sendsms, String order_money, int reduce_money, String pay_money) throws Exception {
        JSONObject jsonObject = requestParam(service_id, source, uid, card_type_id, card_id, account_no, cid, mer_item_id, book_day, other_money_name, other_money, book_phone, note, create_staff_id, items, is_sendsms, order_money, reduce_money, pay_money);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + CREATE_TENNIS_ORDER, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

    //足球订单
    public ResultBean createFootballOrder(String service_id, int source, String uid, int card_type_id, int card_id, String account_no,
                                        String cid, String mer_item_id, String book_day, String other_money_name, String other_money, String book_phone,
                                        String note, String create_staff_id, String items, String is_sendsms, String order_money, int reduce_money, String pay_money) throws Exception {
        JSONObject jsonObject = requestParam(service_id, source, uid, card_type_id, card_id, account_no, cid, mer_item_id, book_day, other_money_name, other_money, book_phone, note, create_staff_id, items, is_sendsms, order_money, reduce_money, pay_money);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + CREATE_FOOTBALL_ORDER, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }







    /**
     * 组织创建订单请求参数
     *
     * @param service_id       服务ID
     * @param source           订单来源
     * @param uid              用户ID
     * @param card_type_id     卡种ID
     * @param card_id          卡ID
     * @param account_no       账户编号
     * @param cid              运动类型
     * @param mer_item_id      商品明细ID
     * @param book_day         预订日期
     * @param other_money_name 其他费用名称
     * @param other_money      其他费用
     * @param book_phone       预订手机号
     * @param note             备注
     * @param create_staff_id  创建人ID
     * @param items            预订明细
     *                         商品价格ID  mer_price_id
     *                         原订单明细金额(分) order_money
     *                         减免金额 reduce_money
     *                         是否使用权益 is_rights 0否1是
     *                         支付金额 pay_money
     *                         运动时间 play_time
     *                         运动人姓名 play_person_name
     *                         运动开始时间 start_hour 默认0
     *                         运动结束时间 end_hour 默认24
     * @param is_sendsms       是否发送短信   0否1是默认1
     * @param order_money      订单金额
     * @param reduce_money     支付金额
     * @param pay_money        减免金额
     * @return
     */
    private JSONObject requestParam(String service_id, int source, String uid, int card_type_id, int card_id, String account_no, String cid, String mer_item_id, String book_day, String other_money_name, String other_money, String book_phone, String note, String create_staff_id, String items, String is_sendsms, String order_money, int reduce_money, String pay_money) {
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
        return jsonObject;
    }


}
