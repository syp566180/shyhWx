//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {
    public SignUtil() {
    }

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{ParameterUtil.WxConfig.TO_KEN, timestamp, nonce};
        System.out.println(ParameterUtil.WxConfig.TO_KEN);
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();

        for(int i = 0; i < arr.length; ++i) {
            content.append(arr[i]);
        }

        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException var8) {
            var8.printStackTrace();
        }

        content = null;
        return tmpStr != null?tmpStr.equals(signature.toUpperCase()):false;
    }

    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";

        for(int i = 0; i < byteArray.length; ++i) {
            strDigest = strDigest + byteToHexStr(byteArray[i]);
        }

        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[]{Digit[mByte >>> 4 & 15], Digit[mByte & 15]};
        String s = new String(tempArr);
        return s;
    }
}
