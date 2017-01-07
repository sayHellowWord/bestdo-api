package com.saidian.utils.http;

import com.google.common.base.Strings;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Http Service
 * Created by bjliuhongbin on 14-7-18.
 */
public class HttpClient {

    private org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();

    {
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    }

    public void doGet(Request request, Response response) throws Exception {

        String url = request.getUrl();
        GetMethod method = new GetMethod(url);
        method.getParams().setContentCharset(request.getCharset());
        setRequestHeader(request, method);
        NameValuePair[] parameters = getRequestParameters(request);
        method.setQueryString(parameters);
        client.executeMethod(method);
        int statusCode = method.getStatusCode();
        response.setResponseCode(statusCode);
        response.setResponseData(method.getResponseBodyAsString());
        String cookies = readCookieAsString();
        response.setCookie(cookies);

        if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                || (statusCode == HttpStatus.SC_SEE_OTHER)
                || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {

            redirect(method, request, response);
        } else {
            response.setNextRefer(url);
            method.releaseConnection();
        }
    }

    public void doPost(Request request, Response response) throws Exception {

        String url = request.getUrl();
        PostMethod method = new PostMethod(url);
        method.getParams().setContentCharset(request.getCharset());
        setRequestHeader(request, method);
        if (Strings.isNullOrEmpty(request.getBodyString())) {
            NameValuePair[] parameters = getRequestParameters(request);
            method.setRequestBody(parameters);
        } else {
            method.setRequestBody(request.getBodyString());
        }
        client.setConnectionTimeout(1000 * 10);
        client.executeMethod(method);
        int statusCode = method.getStatusCode();
        response.setResponseCode(statusCode);
        response.setResponseData(method.getResponseBodyAsString());
        String cookies = readCookieAsString();
        response.setCookie(cookies);

        if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                || (statusCode == HttpStatus.SC_SEE_OTHER)
                || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {

            redirect(method, request, response);
        } else {
            response.setNextRefer(url);
            method.releaseConnection();
        }
    }

    public void doDelete(Request request, Response response) throws Exception {
        String url = request.getUrl();
        DeleteMethod method = new DeleteMethod(url);
        method.getParams().setContentCharset(request.getCharset());
        setRequestHeader(request, method);
        NameValuePair[] parameters = getRequestParameters(request);
        method.setQueryString(parameters);
        client.executeMethod(method);
        int statusCode = method.getStatusCode();
        response.setResponseCode(statusCode);
        response.setResponseData(method.getResponseBodyAsString());
        String cookies = readCookieAsString();
        response.setCookie(cookies);

        if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                || (statusCode == HttpStatus.SC_SEE_OTHER)
                || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {

            redirect(method, request, response);
        } else {
            response.setNextRefer(url);
            method.releaseConnection();
        }
    }

    public void doPut(Request request, Response response) throws Exception {
        String url = request.getUrl();
        PutMethod method = new PutMethod(url);
        method.getParams().setContentCharset(request.getCharset());
        setRequestHeader(request, method);
        NameValuePair[] parameters = getRequestParameters(request);
        method.setQueryString(parameters);
        client.executeMethod(method);
        int statusCode = method.getStatusCode();
        response.setResponseCode(statusCode);
        response.setResponseData(method.getResponseBodyAsString());
        String cookies = readCookieAsString();
        response.setCookie(cookies);

        if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                || (statusCode == HttpStatus.SC_SEE_OTHER)
                || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {

            redirect(method, request, response);
        } else {
            response.setNextRefer(url);
            method.releaseConnection();
        }
    }

    private void redirect(HttpMethod method, Request request, Response response) throws Exception {
        method.releaseConnection();

        // 读取新的 URL 地址
        Header header = method.getResponseHeader("location");
        if (header != null) {
            String newuri = header.getValue();
            if ((newuri == null) || (newuri.equals(""))) {
                newuri = "/";
            }

            URL url = new URL(newuri);
            String refer = request.getUrl();
            String host = url.getHost();
            request.setUrl(newuri);
            request.getRequestHeader().put("Host", host);
            request.getRequestHeader().put("Referer", refer);

            this.doGet(request, response);
        }
    }

    private Cookie[] readCookie() {
        return client.getState().getCookies();
    }

    private String readCookieAsString() {
        Cookie[] cookies = readCookie();
        if (cookies == null || cookies.length <= 0) {
            return "";
        }

        StringBuilder ret = new StringBuilder();
        for (Cookie cookie : cookies) {
            if (ret.length() > 0) {
                ret.append(";");
            }

            ret.append(cookie.toString());
        }

        return ret.toString();
    }

    private void setRequestHeader(Request request, HttpMethod method) {
        Map<String, String> requestHeader = request.getRequestHeader();
        Set<Map.Entry<String, String>> entrySet = requestHeader.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            method.setRequestHeader(key, value);
        }
    }

    private NameValuePair[] getRequestParameters(Request request) {
        List<String[]> parameters = request.getParameters();
        NameValuePair[] ret = new NameValuePair[parameters.size()];
        for (int i = 0; i < ret.length; i++) {
            String[] parameter = parameters.get(i);
            ret[i] = new NameValuePair(parameter[0], parameter[1]);
        }

        return ret;
    }
}
