package com.saidian.web.bean;

/**
 * Created by Administrator on 2017/1/5.
 */
public class GoodsType {

    private String id;

    private String merid;

    private String name;

    private String cid;

    private String alias;

    private String sport;

    private String imgurl;

    private String client_name;

    //商品明细ids(多个用逗号分隔)
    private String mer_item_ids;

    @Override
    public String toString() {
        return "GoodsType{" +
                "id='" + id + '\'' +
                ", merid='" + merid + '\'' +
                ", name='" + name + '\'' +
                ", cid='" + cid + '\'' +
                ", alias='" + alias + '\'' +
                ", sport='" + sport + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", client_name='" + client_name + '\'' +
                ", mer_item_ids='" + mer_item_ids + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getMer_item_ids() {
        return mer_item_ids;
    }

    public void setMer_item_ids(String mer_item_ids) {
        this.mer_item_ids = mer_item_ids;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}
