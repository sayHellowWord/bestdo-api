package com.saidian.config;

/**
 * Created by Administrator on 2017/1/4.
 */
public class HttpParams {

    /**
     * 基础服务URL
     */
    public static String url;

    public static String key;

    public static String serviceId;

    public static String serviceVersion;

    public static String serviceType;

    public static String cardId;//卡种id

    public static String oddscardId;//特惠卡种id

    public static String cityId;

    public static String merid;//商品id

    public static String merPriceId;//商品价格id

    public static int DEFAULT_PAGE = 1;//默认分页-起始页码

    public static int DEFAULT_PAGE_SIZE = 10;//默认分页-每页大小

    public static String CMS_URL;//cms访问地址

    public static int ORDER_SOURCE;//订单来源

    public static int mid; //支付-接入方ID

    public static String project_no;//项目编号

    public static String weixin_url;//微信支付地址

    public static String weixin_key;//微信支付秘钥

    public static int DEFAULT_PAGE_CMS = 1;//默认分页-起始页码

    public static int DEFAULT_PAGE_SIZE_CMS = 30;//默认分页-每页大小

}
