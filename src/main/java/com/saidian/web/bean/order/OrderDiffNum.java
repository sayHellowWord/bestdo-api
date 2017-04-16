package com.saidian.web.bean.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Administrator on 2017/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDiffNum {
    private String all_num;
    private String unpay_num;
    private String confirm_num;
    private String off_num;
    private String over_num;

    public String getAll_num() {
        return all_num;
    }

    public void setAll_num(String all_num) {
        this.all_num = all_num;
    }

    public String getUnpay_num() {
        return unpay_num;
    }

    public void setUnpay_num(String unpay_num) {
        this.unpay_num = unpay_num;
    }

    public String getConfirm_num() {
        return confirm_num;
    }

    public void setConfirm_num(String confirm_num) {
        this.confirm_num = confirm_num;
    }

    public String getOff_num() {
        return off_num;
    }

    public void setOff_num(String off_num) {
        this.off_num = off_num;
    }

    public String getOver_num() {
        return over_num;
    }

    public void setOver_num(String over_num) {
        this.over_num = over_num;
    }
}
