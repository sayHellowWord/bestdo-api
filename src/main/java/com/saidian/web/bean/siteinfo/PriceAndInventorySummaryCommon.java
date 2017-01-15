package com.saidian.web.bean.siteinfo;

/**
 * 时段性-可预订日期
 */
public class PriceAndInventorySummaryCommon {

    private int inventory_summaray;

    private PriceSummaray priceSummaray;

    private String status;

    private String week;//星期几，如果是当天则显示今日

    private String formatDay;//格式化日期 几月几号

    private String mer_item_id;

    private String mer_price_id;

    private String cid;


    public int getInventory_summaray() {
        return inventory_summaray;
    }

    public void setInventory_summaray(int inventory_summaray) {
        this.inventory_summaray = inventory_summaray;
    }

    public PriceSummaray getPriceSummaray() {
        return priceSummaray;
    }

    public void setPriceSummaray(PriceSummaray priceSummaray) {
        this.priceSummaray = priceSummaray;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getFormatDay() {
        return formatDay;
    }

    public void setFormatDay(String formatDay) {
        this.formatDay = formatDay;
    }

    public String getMer_item_id() {
        return mer_item_id;
    }

    public void setMer_item_id(String mer_item_id) {
        this.mer_item_id = mer_item_id;
    }

    public String getMer_price_id() {
        return mer_price_id;
    }

    public void setMer_price_id(String mer_price_id) {
        this.mer_price_id = mer_price_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
