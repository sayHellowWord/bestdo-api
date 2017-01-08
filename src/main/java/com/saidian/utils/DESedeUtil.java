package com.saidian.utils;

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

}