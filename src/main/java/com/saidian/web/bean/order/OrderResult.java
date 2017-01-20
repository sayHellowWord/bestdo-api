package com.saidian.web.bean.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResult {

    private String total;
    private String page;
    private String pagesize;
    private String[] status;

    private OrderDiffNum orderDiffNum;

    private String[] process_status;

    private String[] certificate_status;

    private String cid;

    private List<Order> orders;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

    public OrderDiffNum getOrderDiffNum() {
        return orderDiffNum;
    }

    public void setOrderDiffNum(OrderDiffNum orderDiffNum) {
        this.orderDiffNum = orderDiffNum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String[] getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String[] process_status) {
        this.process_status = process_status;
    }

    public String[] getCertificate_status() {
        return certificate_status;
    }

    public void setCertificate_status(String[] certificate_status) {
        this.certificate_status = certificate_status;
    }

    @Override
    public String toString() {
        return "OrderResult{" +
                "total='" + total + '\'' +
                ", page='" + page + '\'' +
                ", pagesize='" + pagesize + '\'' +
                ", status=" + Arrays.toString(status) +
                ", orderDiffNum=" + orderDiffNum +
                ", process_status=" + Arrays.toString(process_status) +
                ", certificate_status=" + Arrays.toString(certificate_status) +
                ", cid='" + cid + '\'' +
                ", orders=" + orders +
                '}';
    }
}
