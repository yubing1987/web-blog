package com.ybjx.blog.common;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5相关的计算
 * @author ybjx
 * @date 2019/5/2 12:16
 */
public class Md5 {

    /**
     * 计算MD5值
     * @param str 待计算的字符串
     * @return 计算结果
     */
    public static String md5(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] md5Array = md5.digest();
            return byteArrayToHex(md5Array);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    /**
     * 字符数组转字符串
     * @param byteArray 待转换的字符数组
     * @return 转换结果
     */
    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

}
