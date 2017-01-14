package com.saidian.web.bean.siteinfo;

/**
 * Created by Administrator on 2017/1/5.
 */
public class BookDay {

    private String day;

    private int status; //status为0时该天不可预订，status为1时该天可预订

    private String formatDay;

    private String week;//星期几，如果是当天则显示今日

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

    public String getFormatDay() {
        return formatDay;
    }

    public void setFormatDay(String formatDay) {
        this.formatDay = formatDay;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "BookDay{" +
                "day='" + day + '\'' +
                ", status=" + status +
                ", formatDay='" + formatDay + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}
