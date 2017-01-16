package com.saidian.config.init;

import com.saidian.bean.ResultBean;
import com.saidian.bean.ServiceBean;
import com.saidian.config.AccessServices;
import com.saidian.config.HttpParams;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务启动完成初始化操作
 * Created by Administrator on 2017/1/4.
 */
@Component
public class ServiceInit implements CommandLineRunner {

    @Autowired
    private Environment env;


    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动，开始执行加载数据等操作<<<<<<<<<<<<<");

        //初始化 http请求通用参数
        HttpParams.url = env.getProperty("service.url");
        HttpParams.serviceId = env.getProperty("service.base.serviceId");
        HttpParams.serviceVersion = env.getProperty("service.base.serviceVersion");
        HttpParams.serviceType = env.getProperty("service.base.serviceType");
        HttpParams.key = env.getProperty("service.base.key");

        //卡种id
        HttpParams.cardId = env.getProperty("service.card.id");

        //城市信息
        HttpParams.cityId = env.getProperty("server.city.id");

        //商品id
        HttpParams.merid = env.getProperty("server.mer.id");

        //商品价格id
        HttpParams.merPriceId = env.getProperty("server.merPrice.id");

        //cms访问地址
        HttpParams.CMS_URL = env.getProperty("server.cms.url");

        //初始化可接入服务
        String data = "{'internalIp':'" + env.getProperty("application.internalIp") + "','internalPort':'" + env.getProperty("application.internalPort") + "'," +
                "'externalIp':'" + env.getProperty("application.externalIp") + "','externalPort':'" + env.getProperty("application.externalPort") + "' }";

        /*String AUTH_URL = "config/v1/api/service/getKeys";
        ResultBean resultBean = HttpResultUtil.result2Bean(HttpUtil.doPost(HttpParams.url + AUTH_URL, data, HttpParams.key));
        JSONObject jsonObject = new JSONObject(resultBean.getData());
        Map accessServices = new HashMap();
        if (jsonObject.has("list")) {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                ServiceBean serviceBean = new ServiceBean();
                serviceBean.setFromServiceUrl(object.getString("fromServiceUrl"));
                serviceBean.setGoalServiceUrl(object.getString("goalServiceUrl"));
                serviceBean.setSercertKey(object.getString("sercertKey"));
                serviceBean.setGoalServiceId(object.getString("goalServiceId"));
                accessServices.put(serviceBean.getGoalServiceId(), serviceBean);
            }
        }
        AccessServices.init(accessServices);*/

        System.out.println(">>>>>>>>>>>>>>>服务启动，结束执行加载数据等操作<<<<<<<<<<<<<");
    }

}
