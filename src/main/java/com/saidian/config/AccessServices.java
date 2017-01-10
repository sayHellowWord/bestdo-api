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
     */
    public static Map<String, ServiceBean> accessServices;

    public static String USER_SERVICE_URL;

    public static String USER_SERVICE_KEY;

    public static String B_TIEM_SERVICE_URL;

    public static String B_TIEM_SERVICE_KEY;


    //平台服务
    public static String PLATFORM_SERVICE_URL;

    public static String PLATFORM_SERVICE_KEY;


    /**
     * 初始化方法
     *
     * @param accessServices
     */
    public static void init(Map<String, ServiceBean> accessServices) {
        accessServices = accessServices;

        USER_SERVICE_URL = accessServices.get("04").getGoalServiceUrl() + "/";
        USER_SERVICE_KEY = accessServices.get("04").getSercertKey();

        B_TIEM_SERVICE_URL = accessServices.get("65").getGoalServiceUrl() + "/";
        B_TIEM_SERVICE_KEY = accessServices.get("65").getSercertKey();

        PLATFORM_SERVICE_URL = accessServices.get("55").getGoalServiceUrl() + "/";
        PLATFORM_SERVICE_KEY = accessServices.get("55").getSercertKey();

    }


}
