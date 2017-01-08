package com.saidian.utils;

import com.saidian.config.HttpParams;
import com.saidian.utils.http.HttpClient;
import com.saidian.utils.http.Request;
import com.saidian.utils.http.Response;
import org.apache.commons.codec.binary.Base64;

/**
 *  Http请求通用工具类
 *  Created by Administrator on 2017/1/4.
 */
public class HttpUtil {

    /**
     * 发送post请求 并将返回结果打包为String
     *
     * @param url  接口地址
     * @param data 请求参数（加密前）
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String data,String key) throws Exception {
        HttpClient client = new HttpClient();
        Request request = new Request();
        request.setUrl(url);
        request.getParameters().add(new String[]{"serviceId", HttpParams.serviceId});
        request.getParameters().add(new String[]{"serviceVersion", HttpParams.serviceVersion});
        request.getParameters().add(new String[]{"serviceType", HttpParams.serviceType});
        data = new String(Base64.encodeBase64(DESedeUtil.encryptMode(key.getBytes(), data.getBytes())));
        request.getParameters().add(new String[]{"data", new String(data)});
        Response response = new Response();

        client.doPost(request, response);

        Object obj = response.getResponseData();
        String result = obj == null ? "" : obj.toString();

        return result;
    }


    /**
     * 发送get请求 并将返回结果打包为String
     *
     * @param url  接口地址
     * @param data 请求参数（加密前）
     * @return
     * @throws Exception
     */
    public static String doGet(String url, String data,String key) throws Exception {
        HttpClient client = new HttpClient();
        Request request = new Request();
        request.setUrl(url);
        request.getParameters().add(new String[]{"serviceId", HttpParams.serviceId});
        request.getParameters().add(new String[]{"serviceVersion", HttpParams.serviceVersion});
        request.getParameters().add(new String[]{"serviceType", HttpParams.serviceType});
        data = new String(Base64.encodeBase64(DESedeUtil.encryptMode(key.getBytes(), data.getBytes())));
        request.getParameters().add(new String[]{"data", new String(data)});
        Response response = new Response();

        client.doGet(request, response);

        Object obj = response.getResponseData();
        String result = obj == null ? "" : obj.toString();

        return result;
    }

}
