package com.saidian.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/1/20.
 */
public class MD5Util {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultString;
    }

    public static String getMd5(byte[] buffer) throws NoSuchAlgorithmException {
        String s  = null;
        char hexDigist[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(buffer);
        byte[] datas = md.digest(); //16个字节的长整数
        char[] str = new char[2*16];
        int k = 0;
        for(int i=0;i<16;i++){
            byte b   = datas[i];
            str[k++] = hexDigist[b>>>4 & 0xf];//高4位
            str[k++] = hexDigist[b & 0xf];//低4位
        }
        s = new String(str);
        return s;
    }


}
