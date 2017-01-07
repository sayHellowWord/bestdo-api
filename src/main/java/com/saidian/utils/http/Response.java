package com.saidian.utils.http;

/**
 * 相应信息
 * Created by bjliuhongbin on 14-7-18.
 */
public class Response {
    private String cookie;
    private int responseCode;
    private String nextRefer;
    private Object responseData;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getNextRefer() {
        return nextRefer;
    }

    public void setNextRefer(String nextRefer) {
        this.nextRefer = nextRefer;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
