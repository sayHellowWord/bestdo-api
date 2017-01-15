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

        private String piece_id;

        private String venue_id;

        private String name;

        private String status;

        private List<HourInfo> hourInfos;

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

        public String getPiece_id() {
            return piece_id;
        }

        public void setPiece_id(String piece_id) {
            this.piece_id = piece_id;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<HourInfo> getHourInfos() {
            return hourInfos;
        }

        public void setHourInfos(List<HourInfo> hourInfos) {
            this.hourInfos = hourInfos;
        }
    }

    public class HourInfo {

        private int hour;

        private int status; //1  可以预定 0 不可以预定 2  已经预定出去了

        private double prepay_price;

        private int canbook; //1可预订 0 不可预订

        private PriceInfo priceInfo;

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getPrepay_price() {
            return prepay_price;
        }

        public void setPrepay_price(double prepay_price) {
            this.prepay_price = prepay_price;
        }

        public int getCanbook() {
            return canbook;
        }

        public void setCanbook(int canbook) {
            this.canbook = canbook;
        }

        public PriceInfo getPriceInfo() {
            return priceInfo;
        }

        public void setPriceInfo(PriceInfo priceInfo) {
            this.priceInfo = priceInfo;
        }
    }

}
