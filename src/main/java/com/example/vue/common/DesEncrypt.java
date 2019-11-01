package com.example.vue.common;

import com.example.vue.common.constant.KeyConstant;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * @author: Tengfei Wang
 * @description:
 * @date: Created in 10:50 2019-06-20
 * @modified by:
 */
public class DesEncrypt {

    /** 密码长度必须是8的倍数 */
    private static final String KEY ="ahi0hb4ui5a23g2bu23gh3a";


    public static void main(String[] args) {
        System.out.println(KEY.length());
        String content = "DESTest";

        System.out.println("加密前：" + content);
        String result = encrypt(content);

        System.out.println("加密后：" + result);
        String decryResult = decrypt(result);
        System.out.println("解密后：" + decryResult);
    }

    /**
     * 加密
     *
     * @param content 待加密内容
     * @return
     */
    public static String encrypt(String content) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);
            byte[] result = cipher.doFinal(content.getBytes());
            return bytesToHexString(result);
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * 解密
     *
     * @param c 待解密内容
     * @return
     */
    public static String decrypt(String c) {
        if (StringUtils.isEmpty(c)) {
            return null;
        }
        byte[] content = hexStringToBytes(c);
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secureKey, random);
            byte[] result = cipher.doFinal(Objects.requireNonNull(content));
            return new String(result);
        } catch (Throwable e) {
            return null;
        }
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) KeyConstant.HEX_STRING.indexOf(c);
    }

}
