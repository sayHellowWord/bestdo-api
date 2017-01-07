package com.saidian.utils.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求信息
 * Created by bjliuhongbin on 14-7-18.
 */
public class Request {
    private String url;
    private String charset = "UTF-8";
    private List<String[]> parameters = new ArrayList<String[]>();
    private String cookie;
    private Map<String, String> requestHeader = new HashMap<String, String>();
    private String bodyString;//请求体数据

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * 设置请求体数据（设置此数据时，Parameters无效）
     * */
    public void setBodyString(String bodyString){
        this.bodyString = bodyString;
    }

    /**
     * 获得请求体数据
     * */
    public String getBodyString(){
        return this.bodyString;
    }

//    public void addCookie(String cookie) {
//        if (StringUtils.isEmpty(cookie)) {
//            return ;
//        }
//
//        if (StringUtils.isEmpty(this.cookie)) {
//            setCookie(cookie);
//            return;
//        }
//
//        Set<String> cookies = new HashSet<String>();
//        StringTokenizer st = new StringTokenizer(this.cookie, ";");
//        while (st.hasMoreTokens()) {
//            String item = st.nextToken();
//            if (StringUtils.isEmpty(item)) continue;
//
//            if (!cookies.contains(item)) {
//                cookies.add(item);
//            }
//        }
//
//        st = new StringTokenizer(cookie, ";");
//        while (st.hasMoreTokens()) {
//            String item = st.nextToken();
//            if (StringUtils.isEmpty(item)) continue;
//
//            if (!cookies.contains(item)) {
//                cookies.add(item);
//            }
//        }
//
//        StringBuilder _ret = new StringBuilder();
//        for (String item : cookies) {
//            if (_ret.length() > 0) {
//                _ret.append(";");
//            }
//
//            _ret.append(item);
//        }
//
//        setCookie(_ret.toString());
//    }

    public List<String[]> getParameters() {
        return parameters;
    }

    public Map<String, String> getRequestHeader() {
        return requestHeader;
    }
}

