package com.saidian.web.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */
public class GoodsInfo {

    private GoodsInfoBasicInfo goodsInfoBasicInfo;

    private List<GoodsInfoPriceRule> priceRules;

    private List<GoogDetail> googDetails;

    public GoodsInfoBasicInfo getGoodsInfoBasicInfo() {
        return goodsInfoBasicInfo;
    }

    public void setGoodsInfoBasicInfo(GoodsInfoBasicInfo goodsInfoBasicInfo) {
        this.goodsInfoBasicInfo = goodsInfoBasicInfo;
    }

    public List<GoodsInfoPriceRule> getPriceRules() {
        return priceRules;
    }

    public void setPriceRules(List<GoodsInfoPriceRule> priceRules) {
        this.priceRules = priceRules;
    }

    public List<GoogDetail> getGoogDetails() {
        return googDetails;
    }

    public void setGoogDetails(List<GoogDetail> googDetails) {
        this.googDetails = googDetails;
    }
}
