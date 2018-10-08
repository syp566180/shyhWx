package com.jsruiyin.wechat.utils;

import com.alibaba.fastjson.JSONObject;
import com.jsruiyin.common.utils.Md5Encrypt;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

/**
 * MD5工具类
 * @author lh
 */
public class MD5Util {
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String md5Str = new String(str);
            return md5Str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对传入的数据提取摘要
     * @param buffer
     * @return
     */
    public final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * MD5生成签名字符串
     *
     * @param map
     *            需签名参数
     * @param key
     *            MD5key
     * @return
     */
    public static String MD5sign(Map<String, Object> map, String key) {
        String genSign = "";
        try {

            String[] signFields = new String[6];
            signFields[0] = "uid";
            signFields[1] = "type";
            signFields[2] = "orderid";
            signFields[3] = "money";
            signFields[4] = "reqtick";
            signFields[5] = "apikey";
            JSONObject param = (JSONObject) JSONObject.toJSON(map);
            // 生成签名原文
            String signSrc = orgSignSrc(signFields, param);
            // MD5的方式签名
            signSrc += "&KEY=" + key;
            genSign = Md5Encrypt.md5(signSrc);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return genSign;
    }



    /**
     * 构建签名原文
     *
     * @param
     * @param param 参数与值的jsonbject
     * @return
     */
    private static String orgSignSrc(String[] signFields, JSONObject param) {
        StringBuffer signSrc = new StringBuffer("");
        if (signFields != null) {
            Arrays.sort(signFields); // 对key按照 字典顺序排序
        }else{
            return signSrc.toString();
        }


        int i = 0;
        for (String field : signFields) {
            signSrc.append(field);
            signSrc.append("=");
            signSrc.append((StringUtils.isEmpty(param.getString(field)) ? ""
                    : param.getString(field)));
            // 最后一个元素后面不加&
            if (i < (signFields.length - 1)) {
                signSrc.append("&");
            }
            i++;
        }
        return signSrc.toString();
    }

    /**
     * MD5验证签名
     * @param map
     * @param key
     * @param sign
     * @return
     */
    public static void vlidateMD5sign(Map<String ,Object> map,String key,String sign) {
        String vsign=MD5sign(map, key);
        System.out.println("MD5验证签名生成的签名："+vsign);
        System.out.println("MD5验证签名生成的签名与原签名是否一致：sign=vsign true?false:"+(vsign.equals(sign)));
    }


}
