package com.saidian.web.bean.siteinfo;

/**
 * 时段性-可预订日期
 */
public class PriceAndInventorySummaryCommon {

    private String inventory_summaray;

    private PriceSummaray priceSummaray;

    private String status;

    public String getInventory_summaray() {
        return inventory_summaray;
    }

    public void setInventory_summaray(String inventory_summaray) {
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
}
