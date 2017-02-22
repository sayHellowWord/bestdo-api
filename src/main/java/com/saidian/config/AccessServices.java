package com.saidian.config;

import com.saidian.bean.ServiceBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/5.
 */
public class AccessServices {

    /**
     * 当前可接入服务
     * 01（基础服务系统）、04（用户中心）、33（企业项目管理接口系统）、55（预定中心C端服务API）、65（b端web-api）70（订单系统）
     * 11(支付中心)
     * 13(公共服务)
     * 51(订场列表)
     */
    public static Map<String, ServiceBean> accessServices;

    //用户中心
    public static String USER_SERVICE_URL;
    public static String USER_SERVICE_KEY;

    //b端web-api
    public static String B_TIEM_SERVICE_URL;
    public static String B_TIEM_SERVICE_KEY;

    //b端web-api >>>>>>> 订场列表
    public static String B_TIEM_SERVICE_URL_LIST;
    public static String B_TIEM_SERVICE_KEY_LIST;

    //平台服务
    public static String PLATFORM_SERVICE_URL;
    public static String PLATFORM_SERVICE_KEY;

    //公共服务
    public static String PUBLIC_SERVICE_URL;
    public static String PUBLIC_SERVICE_KEY;

    //支付中心
    public static String PAY_SERVICE_URL;
    public static String PAY_SERVICE_KEY;
    public static String PAY_BACK_KEY;


    /**
     * 初始化方法
     *
     * @param accessService
     */
    public static void init(Map<String, ServiceBean> accessService) {

        accessServices = accessService;

        USER_SERVICE_URL = accessServices.get("04").getGoalServiceUrl() + "/";
        USER_SERVICE_KEY = accessServices.get("04").getSercertKey();

        B_TIEM_SERVICE_URL = accessServices.get("65").getGoalServiceUrl() + "/";
        B_TIEM_SERVICE_KEY = accessServices.get("65").getSercertKey();

        B_TIEM_SERVICE_URL_LIST = accessServices.get("51").getGoalServiceUrl() + "/";
        B_TIEM_SERVICE_KEY_LIST = accessServices.get("51").getSercertKey();


        PLATFORM_SERVICE_URL = accessServices.get("55").getGoalServiceUrl() + "/";
        PLATFORM_SERVICE_KEY = accessServices.get("55").getSercertKey();

        PUBLIC_SERVICE_URL = accessServices.get("13").getGoalServiceUrl() + "/";
        PUBLIC_SERVICE_KEY = accessServices.get("13").getSercertKey();

        PAY_SERVICE_URL = accessServices.get("11").getGoalServiceUrl() + "/";
        PAY_SERVICE_KEY = accessServices.get("11").getSercertKey();

        PAY_BACK_KEY = accessServices.get("0h").getSercertKey();

    }


}
