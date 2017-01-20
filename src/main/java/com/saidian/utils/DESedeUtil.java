package com.saidian.utils;

import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESedeUtil {
    // public static final byte[] Key = "thDbctWsPaW9iyb1VSMfOE72".getBytes();
    private static final String Algorithm = "DESede";  //定义 加密算法,可用 DES,DESede,Blowfish

    // 加密字符串
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // 解密字符串
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JSONObject resultJSON = new JSONObject("{\"result\":\"succ\",\"money\":\"1\",\"out_order\":\"10w917011917172632\",\"bankname\":\"ALIPAYWAP\"}");
        String resultStr = resultJSON.getString("result");
        String bankname = resultJSON.getString("bankname");
        String out_order = resultJSON.getString("out_order");
        System.out.println("resultStr : " + resultStr + "bankname" + bankname + "out_order" + out_order);
    }

}