package com.saidian.web.platform;

import com.saidian.utils.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/4.
 */
@Service
public class PlatformService {

    private String URL_SERVICE_MODULE = "config/v1/api/service/getKeys";


    public void getServiceModule() throws Exception {
        String data = "{'internalIp':'10.14.22.65','internalPort':'8080','externalIp':'10.14.22.65','externalPort':'8080' }";
        //String result = HttpUtil.doPost(URL_SERVICE_MODULE, data);


    }


    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    public static void main(String[] args) {
        String unicode = "\\u65e5\\u671f\\u9519\\u8bef\\u4e0d\\u53ef\\u9884\\u8ba2";
        String string = unicode2String(unicode) ;

        System.out.println(unicode);

        System.out.println(string);

    }

}
