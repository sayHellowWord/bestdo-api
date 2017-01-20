package com.saidian.bean;

/**
 * Created by Administrator on 2017/1/5.
 */
public enum OrderStatus {

    UNSUBCRIBED(-2, "已退订"),
    CANCEL(-1, "已取消"),
    NO_PAY(0, "未支付"),
    PAID(1, "已支付"),
    DONE(2, "已完成"),
    STAY_OFF(3, "待下场"),
    IN_UNSUBSCRIBE(4, "退订中"),
    CHECKING(5, "确认中"),
    THE_SLOVAK(6, "待取消"),
    FINISHED(7, "已结束");//包括已取消、已完成、已退订


    // 成员变量
    private int index;
    private String name;

    // 构造方法
    private OrderStatus(int index, String name) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (OrderStatus c : OrderStatus.values()) {
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
