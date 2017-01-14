package com.saidian.web.order;

import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import com.saidian.web.bean.siteinfo.BookDay;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

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
    public ResultBean orderLists(String status, String project_no, String cid, String uid, int page, int pagesize) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("project_no", project_no);
        jsonObject.put("cid", cid);
        jsonObject.put("uid", uid);
        jsonObject.put("page", page);
        jsonObject.put("pagesize", pagesize);

        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ORDER_LISTS, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);

        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {
            JSONObject dataJsonObject = new JSONObject(resultBean.getData());

            resultBean.setPage(dataJsonObject.getInt("page"));
            resultBean.setPageSize(dataJsonObject.getInt("pagesize"));
            resultBean.setTotal(dataJsonObject.getInt("total"));

          /*  resultBean.setTotalPage(dataJsonObject.getInt("totalPage"));*/

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println(resultBean.getData());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");


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

        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        if (200 == resultBean.getCode()) {

            System.out.println(resultBean.getData());

        }

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


    public ResultBean orderDetail(String oid, String uid) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid", oid);
        jsonObject.put("uid", uid);
        String result = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + ORDER_DETAIL, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        System.out.println(result);
        ResultBean<BookDay> resultBean = HttpResultUtil.result2Bean(result);
        System.out.println(resultBean.getMsg());
        if (200 == resultBean.getCode()) {
            System.out.println(resultBean.getData());
        }
        return resultBean;
    }




}
