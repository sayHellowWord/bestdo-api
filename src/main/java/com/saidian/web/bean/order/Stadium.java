package com.saidian.web.bean.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Administrator on 2017/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stadium {
    private String stadium_id;
    private String name;
    private String abbreviation;
    private String province;
    private String city;
    private String district;
    private String region;
    private String address;
    private String latitude;
    private String longitude;
    private String city_position_id;
    private String fax;
    private String phone;
    private String stadium_source;
    private String thumb;
    private String level;
    private String chain_name;
    private String is_chain;
    private String type;
    private String check_code_method;
    private String bd_person;
    private String bd_phone;
    private String description;

   // private OnlineTime online_time;

    private String recommend_reason;
    private String bd_status;
    private String status;
    private String create_staff_id;
    private String verify_staff_id;
    private String verify_time;
    private String verify_reason;
    private String create_time;
    private String update_time;
    private String note;
    private String customer_remark;
    private String status_name;
    private String stadium_source_name;
    private String level_name;
    private String stadium_position_name;

    public String getStadium_id() {
        return stadium_id;
    }

    public void setStadium_id(String stadium_id) {
        this.stadium_id = stadium_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity_position_id() {
        return city_position_id;
    }

    public void setCity_position_id(String city_position_id) {
        this.city_position_id = city_position_id;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStadium_source() {
        return stadium_source;
    }

    public void setStadium_source(String stadium_source) {
        this.stadium_source = stadium_source;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getChain_name() {
        return chain_name;
    }

    public void setChain_name(String chain_name) {
        this.chain_name = chain_name;
    }

    public String getIs_chain() {
        return is_chain;
    }

    public void setIs_chain(String is_chain) {
        this.is_chain = is_chain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheck_code_method() {
        return check_code_method;
    }

    public void setCheck_code_method(String check_code_method) {
        this.check_code_method = check_code_method;
    }

    public String getBd_person() {
        return bd_person;
    }

    public void setBd_person(String bd_person) {
        this.bd_person = bd_person;
    }

    public String getBd_phone() {
        return bd_phone;
    }

    public void setBd_phone(String bd_phone) {
        this.bd_phone = bd_phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*
    public OnlineTime getOnline_time() {
        return online_time;
    }

    public void setOnline_time(OnlineTime online_time) {
        this.online_time = online_time;
    }
*/


    public String getRecommend_reason() {
        return recommend_reason;
    }

    public void setRecommend_reason(String recommend_reason) {
        this.recommend_reason = recommend_reason;
    }

    public String getBd_status() {
        return bd_status;
    }

    public void setBd_status(String bd_status) {
        this.bd_status = bd_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_staff_id() {
        return create_staff_id;
    }

    public void setCreate_staff_id(String create_staff_id) {
        this.create_staff_id = create_staff_id;
    }

    public String getVerify_staff_id() {
        return verify_staff_id;
    }

    public void setVerify_staff_id(String verify_staff_id) {
        this.verify_staff_id = verify_staff_id;
    }

    public String getVerify_time() {
        return verify_time;
    }

    public void setVerify_time(String verify_time) {
        this.verify_time = verify_time;
    }

    public String getVerify_reason() {
        return verify_reason;
    }

    public void setVerify_reason(String verify_reason) {
        this.verify_reason = verify_reason;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCustomer_remark() {
        return customer_remark;
    }

    public void setCustomer_remark(String customer_remark) {
        this.customer_remark = customer_remark;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getStadium_source_name() {
        return stadium_source_name;
    }

    public void setStadium_source_name(String stadium_source_name) {
        this.stadium_source_name = stadium_source_name;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getStadium_position_name() {
        return stadium_position_name;
    }

    public void setStadium_position_name(String stadium_position_name) {
        this.stadium_position_name = stadium_position_name;
    }
}
