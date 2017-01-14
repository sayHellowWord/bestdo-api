package com.saidian.web.bean.siteinfo;

/**
 * 时段性-可选日期概括
 */
public class PriceSummaray {
    private String price_summaray;
    private String mer_item_price_summary_id;
    private String mer_price_id;
    private String mer_item_id;
    private String day;
    private String min_price;
    private String max_price;

    public String getPrice_summaray() {
        return price_summaray;
    }

    public void setPrice_summaray(String price_summaray) {
        this.price_summaray = price_summaray;
    }

    public String getMer_item_price_summary_id() {
        return mer_item_price_summary_id;
    }

    public void setMer_item_price_summary_id(String mer_item_price_summary_id) {
        this.mer_item_price_summary_id = mer_item_price_summary_id;
    }

    public String getMer_price_id() {
        return mer_price_id;
    }

    public void setMer_price_id(String mer_price_id) {
        this.mer_price_id = mer_price_id;
    }

    public String getMer_item_id() {
        return mer_item_id;
    }

    public void setMer_item_id(String mer_item_id) {
        this.mer_item_id = mer_item_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }
}
