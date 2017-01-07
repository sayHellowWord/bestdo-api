package com.saidian.bean;

/**
 * Created by Administrator on 2017/1/5.
 */
public enum ValidType {

    EMAIL_URL(0, "邮件URL验证"), TEL_CODE(1, "手机验证码验证"), EMAIL_CODE(2, "邮箱证验码验证"), EMAIL_PWD_URL(3, "找回密码邮件验证");

    // 成员变量
    private int index;
    private String name;

    // 构造方法
    private ValidType(int index,String name) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ValidType c : ValidType.values()) {
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
