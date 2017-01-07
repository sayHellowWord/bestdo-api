package com.saidian.web.bean;

/**
 * Created by Administrator on 2017/1/5.
 */
public class GoodsInfoBasicInfo {

    private double merid;
    private String name;
    private double cid;
    private double order_process_rules;
    private double book_rules;
    private double is_refund_rules;
    private double refund_rules_hour;
    private String refund_rules;
    private double status;
    private double staff_id;
    private String update_time;
    private String create_time;

    public double getMerid() {
        return merid;
    }

    public void setMerid(double merid) {
        this.merid = merid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCid() {
        return cid;
    }

    public void setCid(double cid) {
        this.cid = cid;
    }

    public double getOrder_process_rules() {
        return order_process_rules;
    }

    public void setOrder_process_rules(double order_process_rules) {
        this.order_process_rules = order_process_rules;
    }

    public double getBook_rules() {
        return book_rules;
    }

    public void setBook_rules(double book_rules) {
        this.book_rules = book_rules;
    }

    public double getIs_refund_rules() {
        return is_refund_rules;
    }

    public void setIs_refund_rules(double is_refund_rules) {
        this.is_refund_rules = is_refund_rules;
    }

    public double getRefund_rules_hour() {
        return refund_rules_hour;
    }

    public void setRefund_rules_hour(double refund_rules_hour) {
        this.refund_rules_hour = refund_rules_hour;
    }

    public String getRefund_rules() {
        return refund_rules;
    }

    public void setRefund_rules(String refund_rules) {
        this.refund_rules = refund_rules;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public double getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(double staff_id) {
        this.staff_id = staff_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "GoodsInfoBasicInfo{" +
                "merid=" + merid +
                ", name='" + name + '\'' +
                ", cid=" + cid +
                ", order_process_rules=" + order_process_rules +
                ", book_rules=" + book_rules +
                ", is_refund_rules=" + is_refund_rules +
                ", refund_rules_hour=" + refund_rules_hour +
                ", refund_rules='" + refund_rules + '\'' +
                ", status=" + status +
                ", staff_id=" + staff_id +
                ", update_time='" + update_time + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
