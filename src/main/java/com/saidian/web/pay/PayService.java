package com.saidian.web.pay;

import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.config.HttpParams;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import com.saidian.utils.MD5Util;
import com.saidian.utils.http.HttpClient;
import com.saidian.utils.http.Request;
import com.saidian.utils.http.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class PayService {

    //支付充值消费接口
    private static String PAY_BANK = "api/pay_bank";

    //支付回调
    private static String PAYMENT_TODO = "payment/todo";


    //支付充值消费接口
    public ResultBean payBank(Integer mid, String order_id, String order_uid, String goods_id, String desc, Integer amount,
                              String channel_id, String user_ip, String appid, Integer time_expir, String openid, String product_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mid", mid);
        jsonObject.put("order_id", order_id);
        jsonObject.put("order_uid", order_uid);
        jsonObject.put("goods_id", goods_id);
        jsonObject.put("desc", desc);
        jsonObject.put("amount", amount);
        jsonObject.put("channel_id", channel_id);
        jsonObject.put("user_ip", user_ip);
        jsonObject.put("appid", appid);
        jsonObject.put("time_expir", time_expir);
        jsonObject.put("openid", openid);

        String result = HttpUtil.doPost(AccessServices.PAY_SERVICE_URL + PAY_BANK, jsonObject.toString(), AccessServices.PAY_SERVICE_KEY);
        ResultBean resultBean = HttpResultUtil.result2Bean(result);
        return resultBean;
    }

    /**
     * 微信支付
     *
     * @param order
     * @param amount
     * @param url
     * @return
     * @throws Exception
     */
    public ResultBean weixinPayBank(String order, String amount, Integer url) throws Exception {
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("order", order);
        parameters.put("amount", amount);
        parameters.put("url", url);

        String characterEncoding = "UTF-8";
        String mySign = createSign(characterEncoding, parameters);

//        System.out.println("mySign   :  " + mySign);

        mySign = mySign.substring(0, mySign.length() / 2);


        HttpClient client = new HttpClient();
        Request request = new Request();
        request.setUrl(HttpParams.weixin_url);
        request.getParameters().add(new String[]{"o", order});
        request.getParameters().add(new String[]{"a", amount});
        request.getParameters().add(new String[]{"s", mySign});
        request.getParameters().add(new String[]{"l", 5 + ""});
        Response response = new Response();

        client.doGet(request, response);

        Object obj = response.getResponseData();
        String result = obj == null ? "" : obj.toString();

//        System.out.println(result);


        return null;
    }

    /**
     * 获取加密sign
     * @param order
     * @param amount
     * @param url
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String getWeiXinPaySign(String order, String amount, Integer url) throws NoSuchAlgorithmException {
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("order", order);
        parameters.put("amount", amount);
        parameters.put("url", url);

        String characterEncoding = "UTF-8";
        String mySign = createSign(characterEncoding, parameters);

        mySign = mySign.substring(0, mySign.length() / 2);
        return mySign;
    }


    /**
     * 支付回调
     *
     * @param result
     * @param bankname
     * @param out_order
     * @return
     * @throws Exception
     */
    public ResultBean payBankCallBack(String result, String bankname, String out_order) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("bankname", bankname);
        jsonObject.put("out_order", out_order);
        String resultStr = HttpUtil.doPost(AccessServices.B_TIEM_SERVICE_URL + PAYMENT_TODO, jsonObject.toString(), AccessServices.B_TIEM_SERVICE_KEY);
        ResultBean resultBean = HttpResultUtil.result2Bean(resultStr);
        return resultBean;
    }


    /**
     * 微信支付签名算法sign
     *
     * @param characterEncoding
     * @param parameters
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) throws NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + HttpParams.weixin_key);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        //String sign = MD5Util.getMd5(sb.toString().getBytes()).toUpperCase();
        return sign;
    }


}
