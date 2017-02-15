package com.saidian.web.order;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidian.bean.OrderStatus;
import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import com.saidian.web.bean.order.Order;
import com.saidian.web.bean.order.OrderResult;
import com.saidian.web.bean.siteinfo.BookDay;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/1/5.
 */
@Service
public class OrderService {

    private static String ORDER_LISTS = "order/lists";

    private static String ORDER_CANCEL = "order/cancel";

    private static String ORDER_UNSUBSCRIBE = "order/unsubscribe";

    private static String ORDER_USER_ORDERS_COUNT = "order/getUserOrdersCount";

    private static String ORDER_DETAIL = "order/detail";

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param status     status = -2 已退订
     *                   status = -1 已取消
     *                   status = 0  未支付
     *                   status = 1  已支付
     *                   status = 2  已完成
     *                   status = 3  待下场
     *                   status = 4  退订中
     *                   status = 5  确认中
     *                   status = 6  待取消
     *                   status = 7  已结束包括  已取消，已完成，已退订
     * @param project_no
     * @param cid
     * @param uid        用户id（必填）
     * @param page
     * @param pagesize
     * @return
     * @throws Exception
     */
    public ResultBean<OrderResult> orderLists(String status, String project_no, String cid, String uid, int page, int pagesize) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("project_no", project_no);
        jsonObject.put("cid", cid);
        jsonObject.put("uid", uid);
        jsonObject.put("page", page);
        jsonObject.put("pagesize", pagesize);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ORDER_LISTS, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean<OrderResult> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            OrderResult orderResult = null;
            try {
                objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
                orderResult = objectMapper.readValue(resultBean.getData().toString(), OrderResult.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Order> orders = orderResult.getOrders();
            if (!CollectionUtils.isEmpty(orders)) {
                DateTime dateTime = new DateTime();
                for (Order order : orders) {
                    dateTime = new DateTime(order.getBook_day());
                    order.setTime(dateTime.toString("MM月dd日") + " " + dateTime.toString("EE", Locale.CHINESE));
                    order.setMoney(Double.parseDouble(order.getOrder_money()) / 100);
                    order.setStatusName(OrderStatus.getName(Integer.parseInt(order.getStatus())));
                }
            }
            orderResult.setOrders(orders);

            resultBean.setObject(orderResult);
            resultBean.setData(StringUtils.EMPTY);

            /*JSONObject dataJson = new JSONObject(resultBean.getData().toString());
            if (dataJson.has("orders") && dataJson.optJSONArray("orders") != null) {
                JSONArray ordersJsonArr = dataJson.optJSONArray("orders");
                List<Order> orderList = new ArrayList<Order>();
                for (int i = 0; i < ordersJsonArr.length(); i++) {
                    JSONObject orderJson = ordersJsonArr.getJSONObject(i);
                    Order order = null;
                    try {
                        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
                        order = objectMapper.readValue(orderJson.toString(), Order.class);
                        orderList.add(order);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(orderList);
                resultBean.setLists(orderList);
            }*/
        }
        return resultBean;
    }

    /**
     * 取消订单
     *
     * @param oid 订单ID
     * @param uid 用户ID
     * @return
     * @throws Exception
     */
    public ResultBean orderCancel(String oid, String uid) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid", oid);
        jsonObject.put("uid", uid);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ORDER_CANCEL, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);

        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {

            System.out.println(resultBean.getData());

        }

        return resultBean;
    }

    /**
     * 退订订单操作
     *
     * @param oid
     * @param uid
     * @param refund_reason
     * @return
     * @throws Exception
     */
    public ResultBean orderUnsubscribe(String oid, String uid, String refund_reason) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid", oid);
        jsonObject.put("uid", uid);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ORDER_UNSUBSCRIBE, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean resultBean = HttpResultUtil.result2Bean(result);
        return resultBean;
    }


    /**
     * @param uid            用户ID
     * @param project_id     项目ID
     * @param start_day      开始日期
     * @param end_day        结束日期
     * @param status         状态（1,2）
     * @param process_status 处理流程状态 （300,301）
     * @param mer_price_id   价格ID
     * @return
     */
    /*public ResultBean getUserOrdersCount(String uid, String project_id, String start_day, String end_day, String status, String process_status, String mer_price_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("project_id", project_id);
        jsonObject.put("start_day", start_day);
        jsonObject.put("end_day", end_day);
        jsonObject.put("status", status);
        jsonObject.put("process_status", process_status);
        jsonObject.put("mer_price_id", mer_price_id);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ORDER_USER_ORDERS_COUNT, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        System.out.println(result);
        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        System.out.println(resultBean.getMsg());

        if (200 == resultBean.getCode()) {
            System.out.println(resultBean.getData());
        }
        return resultBean;

    }*/

    /**
     * 订单详情
     *
     * @param oid
     * @param uid
     * @return
     * @throws Exception
     */
    public ResultBean<Order> orderDetail(String oid, String uid) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid", oid);
        jsonObject.put("uid", uid);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ORDER_DETAIL, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);

        System.out.println("oid:" + oid + "uid:" + uid + ">>>>>>>>>>>>>>>>>>订单详情获取:" + result);

        ResultBean<Order> orderResultBean = HttpResultUtil.result2Bean(result);
        if (200 == orderResultBean.getCode()) {
            Order order = null;
            try {
                objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
                order = objectMapper.readValue(orderResultBean.getData().toString(), Order.class);
                DateTime dateTime = new DateTime(order.getBook_day());
                order.setTime(dateTime.toString("MM月dd日") + " " + dateTime.toString("EE", Locale.CHINESE));
                order.setMoney(Double.parseDouble(order.getOrder_money()) / 100);
                order.setStatusName(OrderStatus.getName(Integer.parseInt(order.getStatus())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            orderResultBean.setObject(order);
            orderResultBean.setData(StringUtils.EMPTY);
        }
        return orderResultBean;
    }


}
