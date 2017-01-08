package com.saidian.utils;

import com.saidian.bean.ResultBean;
import com.saidian.web.bean.GoogDetail;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/5.
 */
public class HttpResultUtil {


    public static ResultBean result2Bean(String data) {
        JSONObject jsonObject = new JSONObject(data);
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(jsonObject.getInt("code"));

        if (jsonObject.has("msg"))
            resultBean.setMsg(jsonObject.getString("msg"));

        if (jsonObject.has("data"))
            resultBean.setData(jsonObject.getString("data"));

        return resultBean;
    }


    public static void item2GoodDetail(GoogDetail googDetail, JSONObject goodDetailJsonObject) {
        if (goodDetailJsonObject.has("mer_item_id")) {
            googDetail.setMer_item_id(goodDetailJsonObject.get("mer_item_id").toString());
        }
        if (goodDetailJsonObject.has("mer_price_id")) {
            googDetail.setMer_price_id(goodDetailJsonObject.getDouble("mer_price_id"));
        }
        if (goodDetailJsonObject.has("merid")) {
            googDetail.setMerid(goodDetailJsonObject.getInt("merid"));
        }
        if (goodDetailJsonObject.has("cid")) {
            googDetail.setCid(goodDetailJsonObject.getDouble("cid"));
        }
        if (goodDetailJsonObject.has("name")) {
            googDetail.setName(goodDetailJsonObject.getString("name"));
        }
        if (goodDetailJsonObject.has("address")) {
            googDetail.setAddress(goodDetailJsonObject.getString("address"));
        }
        if (goodDetailJsonObject.has("position")) {
            googDetail.setPosition(goodDetailJsonObject.getString("position"));
        }
        if (goodDetailJsonObject.has("price_info")) {
            googDetail.setPrice_info(goodDetailJsonObject.getString("price_info"));
        }
        if (goodDetailJsonObject.has("thumb")) {
            googDetail.setThumb(goodDetailJsonObject.getString("thumb"));
        }
        if (goodDetailJsonObject.has("province")) {
            googDetail.setProvince(goodDetailJsonObject.getDouble("province"));
        }
        if (goodDetailJsonObject.has("city")) {
            googDetail.setCity(goodDetailJsonObject.getDouble("city"));
        }
        if (goodDetailJsonObject.has("district")) {
            googDetail.setDistrict(goodDetailJsonObject.getDouble("district"));
        }
        if (goodDetailJsonObject.has("region")) {
            googDetail.setRegion(goodDetailJsonObject.getString("region"));
        }
        if (goodDetailJsonObject.has("latitude")) {
            googDetail.setLatitude(goodDetailJsonObject.getDouble("latitude") + "");
        }
        if (goodDetailJsonObject.has("longitude")) {
            googDetail.setLongitude(goodDetailJsonObject.getDouble("longitude") + "");
        }
        if (goodDetailJsonObject.has("lat")) {
            googDetail.setLat(goodDetailJsonObject.getDouble("lat") + "");
        }
        if (goodDetailJsonObject.has("lng")) {
            googDetail.setLng(goodDetailJsonObject.getDouble("lng") + "");
        }
        if (goodDetailJsonObject.has("price")) {
            googDetail.setPrice(goodDetailJsonObject.getDouble("price"));
        }

        if (goodDetailJsonObject.has("price_data")) {
            googDetail.setPrice_data(goodDetailJsonObject.getString("price_data"));
        }
        if (goodDetailJsonObject.has("card_type_id")) {
            googDetail.setCard_type_id(goodDetailJsonObject.getDouble("card_type_id"));
        }
        if (goodDetailJsonObject.has("@geodist")) {
            googDetail.set_geodist(goodDetailJsonObject.getDouble("@geodist"));
        }
        if (goodDetailJsonObject.has("geodist")) {
            googDetail.setGeodist(goodDetailJsonObject.getDouble("geodist"));
        }

        if (goodDetailJsonObject.has("title")) {
            googDetail.setTitle(goodDetailJsonObject.getString("title"));
        }
        if (goodDetailJsonObject.has("is_book")) {
            googDetail.setIs_book(goodDetailJsonObject.getString("is_book"));
        }
        if (goodDetailJsonObject.has("is_show")) {
            googDetail.setIs_book(goodDetailJsonObject.getString("is_show"));
        }

        if (goodDetailJsonObject.has("book_rules")) {
            googDetail.setBook_rules(goodDetailJsonObject.getString("book_rules"));
        }
        if (goodDetailJsonObject.has("is_refund_rules")) {
            googDetail.setIs_refund_rules(goodDetailJsonObject.getString("is_refund_rules"));
        }
        if (goodDetailJsonObject.has("refund_rules_hour")) {
            googDetail.setRefund_rules_hour(goodDetailJsonObject.getString("refund_rules_hour"));
        }
        if (goodDetailJsonObject.has("refund_rules")) {
            googDetail.setRefund_rules(goodDetailJsonObject.getString("refund_rules"));
        }
        if (goodDetailJsonObject.has("stadium_id")) {
            googDetail.setStadium_id(goodDetailJsonObject.getString("stadium_id"));
        }
        if (goodDetailJsonObject.has("venue_name")) {
            googDetail.setVenue_name(goodDetailJsonObject.getString("venue_name"));
        }
        if (goodDetailJsonObject.has("stadium_name")) {
            googDetail.setStadium_name(goodDetailJsonObject.getString("stadium_name"));
        }
        if (goodDetailJsonObject.has("note")) {
            googDetail.setNote(goodDetailJsonObject.getString("note"));
        }
        if (goodDetailJsonObject.has("venue_id")) {
            googDetail.setVenue_id(goodDetailJsonObject.getString("venue_id"));
        }
        if (goodDetailJsonObject.has("inventory_type")) {
            googDetail.setInventory_type(goodDetailJsonObject.getString("inventory_type"));
        }
        if (goodDetailJsonObject.has("venue_material")) {
            googDetail.setVenue_material(goodDetailJsonObject.getString("venue_material"));
        }
        if (goodDetailJsonObject.has("payment_method")) {
            googDetail.setPayment_method(goodDetailJsonObject.getString("payment_method"));
        }
        if (goodDetailJsonObject.has("stadium_status")) {
            googDetail.setStadium_status(goodDetailJsonObject.getString("stadium_status"));
        }


        if (goodDetailJsonObject.has("venue_status")) {
            googDetail.setVenue_status(goodDetailJsonObject.getString("venue_status"));
        }
        if (goodDetailJsonObject.has("min_price")) {
            googDetail.setMin_price(goodDetailJsonObject.getString("min_price"));
        }

        if (goodDetailJsonObject.has("status")) {
            googDetail.setStatus(goodDetailJsonObject.getString("status"));
        }
        if (goodDetailJsonObject.has("create_staff_id")) {
            googDetail.setCreate_staff_id(goodDetailJsonObject.getString("create_staff_id"));
        }
        if (goodDetailJsonObject.has("create_time")) {
            googDetail.setCreate_time(goodDetailJsonObject.getString("create_time"));
        }
        if (goodDetailJsonObject.has("update_time")) {
            googDetail.setUpdate_time(goodDetailJsonObject.getString("update_time"));
        }
        if (goodDetailJsonObject.has("verify_staff_id")) {
            googDetail.setVerify_staff_id(goodDetailJsonObject.getString("verify_staff_id"));
        }
        if (goodDetailJsonObject.has("verify_time")) {
            googDetail.setVerify_time(goodDetailJsonObject.getString("verify_time"));
        }
    }

}
