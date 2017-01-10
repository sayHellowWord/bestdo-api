package com.saidian.bean;

/**
 * 注册来源
 */
public enum RegOrigin {

    LOGIN_NAME(0, "用户名"),
    TELEPHONE(1, "手机号"),
    EMAIL(2, "邮箱"),
    QQ(3, "qq"),
    SINAWEIBO(4, "新浪微博"),
    WEIXIN(5, "微信"),
    OTHER(6, "其它");

    // 成员变量
    private int index;
    private String name;

    // 构造方法
    private RegOrigin(int index, String name) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (RegOrigin c : RegOrigin.values()) {
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
