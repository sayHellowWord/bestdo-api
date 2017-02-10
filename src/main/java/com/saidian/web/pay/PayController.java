package com.saidian.web.pay;

import com.google.common.collect.ImmutableBiMap;
import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.config.HttpParams;
import com.saidian.utils.DesBase64Tool;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/17.
 */
@RequestMapping(value = "pay")
@Controller
public class PayController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String LOG_PRE = "pay-error:";

    @Autowired
    PayService payService;

    @RequestMapping(value = "payBank")
    @ResponseBody
    public Object payBank(String order_id, String goods_id, String desc, String amount,
                          String channel_id, String appid, String time_expir, String openid, HttpSession httpSession) throws Exception {

        System.out.println("order_id = [" + order_id + "], goods_id = [" + goods_id + "], desc = [" + desc + "]," +
                " amount = [" + amount + "], channel_id = [" + channel_id + "], appid = [" + appid + "], " +
                "time_expir = [" + time_expir + "], openid = [" + openid + "]");
        //todo udi
        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String uid = jsonObject.getString("uid");

        ResultBean resultBean = null;
        if ("ALIPAYWAP".equals(channel_id)) {
            resultBean = payService.payBank(HttpParams.mid, order_id, uid, goods_id,
                    "调用支付", Integer.parseInt(amount), channel_id, "10.14.22.75", "",
                    300, "", "");

        /*    resultBean = payService.payBank(HttpParams.mid, order_id, "0h1170114160444ihv", goods_id,
                    "调用支付", Integer.parseInt(amount), channel_id, "10.14.22.75", "",
                    300, "", "");*/

        } else if ("WEIXINJSAPI".equals(channel_id)) {
            //payService.weixinPayBank(order_id, amount, 4);
            resultBean = new ResultBean();
            resultBean.setCode(200);
            String mySign = payService.getWeiXinPaySign(order_id, amount, 5);
            resultBean.setObject(mySign);
            resultBean.setData(HttpParams.weixin_url);
        }
        return resultBean;
    }

    @RequestMapping(value = "paySucPage")
    public String paySucPage(String order_id, ModelMap modelMap) {
        System.out.println("paySucPage >>>>>>>>>>>  " + order_id);
        modelMap.addAttribute("order_id", order_id);
        return "/order/paysuc";
    }

    @RequestMapping(value = "payFailPage")
    public String payFailPage(String order_id, ModelMap modelMap) {
        modelMap.addAttribute("order_id", order_id);
        return "/order/payfail";
    }

    @RequestMapping(value = "payTimeOutPage")
    public String payTimeOutPage(String order_id, ModelMap modelMap) {
        modelMap.addAttribute("order_id", order_id);
        return "/order/paytimeout";
    }


    @RequestMapping(value = "payBankCallBack")
    @ResponseBody
    public Object payBankCallBack(String data) {
        System.out.println("data = [" + data + "]");
        logger.error(LOG_PRE + "加密前:data = [" + data + "]");
        try {
            String result = DesBase64Tool.desDecrypt(data, AccessServices.PAY_BACK_KEY);
            logger.error(LOG_PRE + "解密后:data = [" + result + "]");
//            JSONObject resultJSON = new JSONObject("{\"result\":\"succ\",\"money\":\"1\",\"out_order\":\"10w917011917172632\",\"bankname\":\"ALIPAYWAP\"}");
//            String resultStr = resultJSON.getString("result");
//            String bankname = resultJSON.getString("bankname");
//            String out_order = resultJSON.getString("out_order");
//            System.out.println("resultStr : " + resultStr + "bankname" + bankname + "out_order" + out_order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ImmutableBiMap.of("code", 200);
    }


    @RequestMapping(value = "test")
    @ResponseBody
    public Object test() throws Exception {

        return payService.weixinPayBank("10w917011716304280", "1", 5);

    }

}
