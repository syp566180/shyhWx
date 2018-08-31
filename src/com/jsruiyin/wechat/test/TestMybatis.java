package com.jsruiyin.wechat.test;

import com.alibaba.fastjson.JSONArray;
import com.jsruiyin.wechat.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;

/**
 * Created by chenyuping on 2018/8/6.
 */

public class TestMybatis {

    public static void main(String[] args) throws Exception {

//        String nonce_str = WXPayUtil.generateNonceStr();
//        System.out.println(nonce_str);
//        System.out.println(nonce_str.length());
          String data = "{\"SystemCode\":\"1\",\"data\":{\"msg\"=\"恭喜中奖！\", \"prizeAmount\"=1.88, \"drawCount\"=\"2\", \"status\"=\"1\"}}";
          JSONObject jsonObject = JSONObject.fromObject(data).getJSONObject("data");
          System.out.println(jsonObject.getString("msg"));
            System.out.println(jsonObject.getString("prizeAmount"));
            System.out.println(jsonObject.getString("drawCount"));

//        data = {"USERID":1,"PASSWORD":"1","USERNAME":"admin","RNAME":"","PHONEMOBILE":"","PHOTO":"","EMAIL":"","SEX":-1,"STATE":-1,"ADDRESS":-1};
//        JSONObject jsStr = JSONObject.fromObject(str);
//        String s = jsStr.get("USERNAME").toString();
//        System.out.println(a);//{"c":"d","a":"b"}System.out.println(a.get("c"));//d}
    }
}
