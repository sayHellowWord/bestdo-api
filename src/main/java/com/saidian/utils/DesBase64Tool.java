package com.saidian.utils;

import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Java版3DES加密解密，适用于PHP版3DES加密解密(PHP语言开发的MCRYPT_3DES算法、MCRYPT_MODE_ECB模式、PKCS7填充方式)
 * http://www.blogjava.net/qileilove/archive/2013/09/16/404111.html
 *
 * @author G007N
 */
public class DesBase64Tool {
    private static SecretKey secretKey = null;//key对象
    private static Cipher cipher = null;   //私鈅加密对象Cipher
    private static String keyString = "8E7R0kOXh9RCM3O3LdqMxJ2C";//密钥
    private static Logger log = Logger.getRootLogger();

    static {
        try {
            secretKey = new SecretKeySpec(keyString.getBytes(), "DESede");//获得密钥
/*获得一个私鈅加密类Cipher，DESede是算法，ECB是加密模式，PKCS5Padding是填充方式*/
            cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 解密
     *
     * @param message
     * @return
     * @throws Exception
     */
    public static String desDecrypt(String message, String key) throws Exception {
        String result = "";
        try {
            BASE64Decoder dec = new BASE64Decoder();
            byte[] messageBytes = dec.decodeBuffer(message);  //进行BASE64编码
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "DESede"));      //设置工作模式为解密模式，给出密钥
            byte[] resultBytes = cipher.doFinal(messageBytes);//正式执行解密操作
            result = new String(resultBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 加密解密测试
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String desdResult = desDecrypt("YlD5jO05iYVP3BsgMYY7PTp2cKBYEtpfGrxqKL60M9vFf48YK44V3lvlla6XHq2vTgkuaqmsercyiHPpMVsOTKqx7mQBOcAzlDrvFUts6/0pljfPmpdhUQ==","8E7R0kOXh9RCM3O3LdqMxJ2C");//解密
            System.out.println("解密结果：" + desdResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}