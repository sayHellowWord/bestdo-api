package com.saidian.web.bean;

/**
 * Created by Administrator on 2017/1/5.
 */
public class BookDay {

    private String day;

    private int status; //status为0时该天不可预订，status为1时该天可预订

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookDays{" +
                "day='" + day + '\'' +
                ", status=" + status +
                '}';
    }
}
