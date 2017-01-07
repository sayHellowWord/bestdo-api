package com.saidian.bean;

/**
 * Created by Administrator on 2017/1/5.
 */
public class ServiceBean {

    private String fromServiceId;

    private String goalServiceId;

    private String sercertKey;

    private String encryptType;

    private String status;

    private String goalServiceUrl;

    private String fromServiceUrl;


    public String getFromServiceId() {
        return fromServiceId;
    }

    public void setFromServiceId(String fromServiceId) {
        this.fromServiceId = fromServiceId;
    }

    public String getGoalServiceId() {
        return goalServiceId;
    }

    public void setGoalServiceId(String goalServiceId) {
        this.goalServiceId = goalServiceId;
    }

    public String getSercertKey() {
        return sercertKey;
    }

    public void setSercertKey(String sercertKey) {
        this.sercertKey = sercertKey;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoalServiceUrl() {
        return goalServiceUrl;
    }

    public void setGoalServiceUrl(String goalServiceUrl) {
        this.goalServiceUrl = goalServiceUrl;
    }

    public String getFromServiceUrl() {
        return fromServiceUrl;
    }

    public void setFromServiceUrl(String fromServiceUrl) {
        this.fromServiceUrl = fromServiceUrl;
    }

    @Override
    public String toString() {
        return "ServiceBean{" +
                "fromServiceId='" + fromServiceId + '\'' +
                ", goalServiceId='" + goalServiceId + '\'' +
                ", sercertKey='" + sercertKey + '\'' +
                ", encryptType='" + encryptType + '\'' +
                ", status='" + status + '\'' +
                ", goalServiceUrl='" + goalServiceUrl + '\'' +
                ", fromServiceUrl='" + fromServiceUrl + '\'' +
                '}';
    }
}
