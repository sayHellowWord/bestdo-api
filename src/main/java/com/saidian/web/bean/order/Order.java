package com.saidian.web.bean.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Administrator on 2017/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private String oid;
    private String service_id;
    private String source;
    private String uid;
    private String username;
    private String company_id;
    private String project_id;
    private String card_type_id;
    private String card_batch_id;
    private String card_id;
    private String account_rule_id;
    private String account_no;
    private String cid;
    private String stadium_id;
    private String stadium_name;
    private String venue_id;
    private String merid;
    private String mer_item_id;
    private String mer_item_name;
    private String book_day;
    private String order_money;
    private String reduce_money;
    private String other_money_name;
    private String other_money;
    private String pay_money;
    private String pay_channel;
    private String pay_time;
    private String status;
    private String money_status;
    private String certificate_status;
    private String process_type;
    private String process_rules;
    private String process_status;
    private String inventory_type;
    private String is_set_supplier;
    private String supplier_id;
    private String is_supplier_settlement;
    private String book_phone;
    private String note;
    private String create_staff_id;
    private String create_time;
    private String update_time;
    private String is_cycle;
    private String is_test;
    private String user_category;

    List<Item> items;

    private String book_number;
    private String process_rules_name;
    private Stadium stadium;


    //========================  订单列表显示  =====================================
    private String time;
    private double money;
    private int noPay;
    private String statusName;
    //========================  订单列表显示  =====================================


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getCard_type_id() {
        return card_type_id;
    }

    public void setCard_type_id(String card_type_id) {
        this.card_type_id = card_type_id;
    }

    public String getCard_batch_id() {
        return card_batch_id;
    }

    public void setCard_batch_id(String card_batch_id) {
        this.card_batch_id = card_batch_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getAccount_rule_id() {
        return account_rule_id;
    }

    public void setAccount_rule_id(String account_rule_id) {
        this.account_rule_id = account_rule_id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStadium_id() {
        return stadium_id;
    }

    public void setStadium_id(String stadium_id) {
        this.stadium_id = stadium_id;
    }

    public String getStadium_name() {
        return stadium_name;
    }

    public void setStadium_name(String stadium_name) {
        this.stadium_name = stadium_name;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getMer_item_id() {
        return mer_item_id;
    }

    public void setMer_item_id(String mer_item_id) {
        this.mer_item_id = mer_item_id;
    }

    public String getMer_item_name() {
        return mer_item_name;
    }

    public void setMer_item_name(String mer_item_name) {
        this.mer_item_name = mer_item_name;
    }

    public String getBook_day() {
        return book_day;
    }

    public void setBook_day(String book_day) {
        this.book_day = book_day;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public String getReduce_money() {
        return reduce_money;
    }

    public void setReduce_money(String reduce_money) {
        this.reduce_money = reduce_money;
    }

    public String getOther_money_name() {
        return other_money_name;
    }

    public void setOther_money_name(String other_money_name) {
        this.other_money_name = other_money_name;
    }

    public String getOther_money() {
        return other_money;
    }

    public void setOther_money(String other_money) {
        this.other_money = other_money;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoney_status() {
        return money_status;
    }

    public void setMoney_status(String money_status) {
        this.money_status = money_status;
    }

    public String getCertificate_status() {
        return certificate_status;
    }

    public void setCertificate_status(String certificate_status) {
        this.certificate_status = certificate_status;
    }

    public String getProcess_type() {
        return process_type;
    }

    public void setProcess_type(String process_type) {
        this.process_type = process_type;
    }

    public String getProcess_rules() {
        return process_rules;
    }

    public void setProcess_rules(String process_rules) {
        this.process_rules = process_rules;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public String getInventory_type() {
        return inventory_type;
    }

    public void setInventory_type(String inventory_type) {
        this.inventory_type = inventory_type;
    }

    public String getIs_set_supplier() {
        return is_set_supplier;
    }

    public void setIs_set_supplier(String is_set_supplier) {
        this.is_set_supplier = is_set_supplier;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getIs_supplier_settlement() {
        return is_supplier_settlement;
    }

    public void setIs_supplier_settlement(String is_supplier_settlement) {
        this.is_supplier_settlement = is_supplier_settlement;
    }

    public String getBook_phone() {
        return book_phone;
    }

    public void setBook_phone(String book_phone) {
        this.book_phone = book_phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreate_staff_id() {
        return create_staff_id;
    }

    public void setCreate_staff_id(String create_staff_id) {
        this.create_staff_id = create_staff_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getIs_cycle() {
        return is_cycle;
    }

    public void setIs_cycle(String is_cycle) {
        this.is_cycle = is_cycle;
    }

    public String getIs_test() {
        return is_test;
    }

    public void setIs_test(String is_test) {
        this.is_test = is_test;
    }

    public String getUser_category() {
        return user_category;
    }

    public void setUser_category(String user_category) {
        this.user_category = user_category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getBook_number() {
        return book_number;
    }

    public void setBook_number(String book_number) {
        this.book_number = book_number;
    }

    public String getProcess_rules_name() {
        return process_rules_name;
    }

    public void setProcess_rules_name(String process_rules_name) {
        this.process_rules_name = process_rules_name;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getNoPay() {
        return noPay;
    }

    public void setNoPay(int noPay) {
        this.noPay = noPay;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", service_id='" + service_id + '\'' +
                ", source='" + source + '\'' +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", company_id='" + company_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", card_type_id='" + card_type_id + '\'' +
                ", card_batch_id='" + card_batch_id + '\'' +
                ", card_id='" + card_id + '\'' +
                ", account_rule_id='" + account_rule_id + '\'' +
                ", account_no='" + account_no + '\'' +
                ", cid='" + cid + '\'' +
                ", stadium_id='" + stadium_id + '\'' +
                ", stadium_name='" + stadium_name + '\'' +
                ", venue_id='" + venue_id + '\'' +
                ", merid='" + merid + '\'' +
                ", mer_item_id='" + mer_item_id + '\'' +
                ", mer_item_name='" + mer_item_name + '\'' +
                ", book_day='" + book_day + '\'' +
                ", order_money='" + order_money + '\'' +
                ", reduce_money='" + reduce_money + '\'' +
                ", other_money_name='" + other_money_name + '\'' +
                ", other_money='" + other_money + '\'' +
                ", pay_money='" + pay_money + '\'' +
                ", pay_channel='" + pay_channel + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", status='" + status + '\'' +
                ", money_status='" + money_status + '\'' +
                ", certificate_status='" + certificate_status + '\'' +
                ", process_type='" + process_type + '\'' +
                ", process_rules='" + process_rules + '\'' +
                ", process_status='" + process_status + '\'' +
                ", inventory_type='" + inventory_type + '\'' +
                ", is_set_supplier='" + is_set_supplier + '\'' +
                ", supplier_id='" + supplier_id + '\'' +
                ", is_supplier_settlement='" + is_supplier_settlement + '\'' +
                ", book_phone='" + book_phone + '\'' +
                ", note='" + note + '\'' +
                ", create_staff_id='" + create_staff_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", is_cycle='" + is_cycle + '\'' +
                ", is_test='" + is_test + '\'' +
                ", user_category='" + user_category + '\'' +
                ", items=" + items +
                ", book_number='" + book_number + '\'' +
                ", process_rules_name='" + process_rules_name + '\'' +
                ", stadium=" + stadium +
                ", time='" + time + '\'' +
                ", money=" + money +
                ", noPay=" + noPay +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
