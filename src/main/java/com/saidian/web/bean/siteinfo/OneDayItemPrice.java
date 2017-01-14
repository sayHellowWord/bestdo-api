package com.saidian.web.bean.siteinfo;

import java.util.List;

/**
 * 日期型价格
 */
public class OneDayItemPrice {

    private List<InventoryInfo> inventoryInfos;//库存

    private byte destine; //是否可预订 根据库存

    private List<PriceInfo> priceInfos;

    public List<InventoryInfo> getInventoryInfos() {
        return inventoryInfos;
    }

    public void setInventoryInfos(List<InventoryInfo> inventoryInfos) {
        this.inventoryInfos = inventoryInfos;
    }

    public List<PriceInfo> getPriceInfos() {
        return priceInfos;
    }

    public void setPriceInfos(List<PriceInfo> priceInfos) {
        this.priceInfos = priceInfos;
    }

    public byte getDestine() {
        return destine;
    }

    public void setDestine(byte destine) {
        this.destine = destine;
    }

    public class InventoryInfo {

        private String day;

        private int number;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

}
