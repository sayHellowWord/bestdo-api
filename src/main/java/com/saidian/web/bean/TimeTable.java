package com.saidian.web.bean;

/**
 * 时间性-片场小格
 */
public class TimeTable {

    private String money;
    private String piece_id;
    private String start_hour;
    private String end_hour;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPiece_id() {
        return piece_id;
    }

    public void setPiece_id(String piece_id) {
        this.piece_id = piece_id;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }
}
