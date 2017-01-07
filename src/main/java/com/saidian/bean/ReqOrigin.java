package com.saidian.bean;

/**
 * Created by Administrator on 2017/1/5.
 */
public enum ReqOrigin {

    BIND_MOBILE(1, "绑定手机号"),
    MODIFY_BIND_OLD_MOBILE(2, "修改绑定旧手机号"),
    MODIFY_BIND_NEW_MOBILE(3, "修改绑定新手机号"),
    SET_PAY_PASSWORD(4, "设置支付密码"),
    MODIFY_PAY_PASSWORD(5, "修改支付密码"),
    RECHARGE_SUCCESS(6, "充值成功"), REG_VALID(7, "本次注册"),
    FIND_PASSWORD(8, "找回密码"),
    DYNAMIC_PASSWORD(9, "本次登录动态密码"), SET_LOGIN_PASSWORD(10, "设置密码"),
    C_LOGIN_VERIFY(11, "C端注册验证"),
    C_QUICK_LOGIN(12, "C端快捷登录"),
    C_GET_BACK(13, "C端找回密码"),
    C_BINDING_MOBILE(14, "C端绑定手机"),
    C_SET_UP(15, "C端设置登录密码");


    // 成员变量
    private int index;
    private String name;

    // 构造方法
    private ReqOrigin(int index, String name) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ReqOrigin c : ReqOrigin.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
