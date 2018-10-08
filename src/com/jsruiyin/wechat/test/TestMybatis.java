package com.jsruiyin.wechat.test;

import com.alibaba.fastjson.JSONArray;
import com.jsruiyin.wechat.utils.ParameterUtil;
import com.jsruiyin.wechat.utils.PayUtil;
import com.jsruiyin.wechat.utils.WeixinUtil;
import com.jsruiyin.wechat.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;

import java.util.Iterator;

/**
 * Created by chenyuping on 2018/8/6.
 */

public class TestMybatis {

    public static void main(String[] args) throws Exception {

//        String nonce_str = WXPayUtil.generateNonceStr();
//        System.out.println(nonce_str);
//        System.out.println(nonce_str.length());
//          String data = "{\"msg\"=\"恭喜中奖！\", \"prizeAmount\"=1.88, \"drawCount\"=\"2\", \"status\"=\"1\"}";
////        JSONObject jsonObject = JSONObject.fromObject(data).getJSONObject("data");
////            System.out.println(jsonObject.getString("msg"));
////            System.out.println(jsonObject.getString("prizeAmount"));
////            System.out.println(jsonObject.getString("drawCount"));
//        String url = "www.baidu.com";
//        System.out.println(data);
//        JSONObject jsonObject = JSONObject.fromObject(data);
//        System.out.println(jsonObject.toString()+"   "+jsonObject.size());
//        Iterator<String> it = jsonObject.keys();
//        while(it.hasNext()){
//            // 获得key
//            String key = it.next();
//            String value = jsonObject.getString(key);
//            System.out.print(key+":"+value);
//        }
//        //String url = ParameterUtil.GETACTIVITYURL;
////        url = url.replace("OPENID","111").
////                replace("NICKNAME","222").
////                replace("STATE","333");
////        System.out.println("url "+url);
//
//        String sb = WeixinUtil.getUrl(url,jsonObject);
//        System.out.println("url "+sb);

//        data = {"USERID":1,"PASSWORD":"1","USERNAME":"admin","RNAME":"","PHONEMOBILE":"","PHOTO":"","EMAIL":"","SEX":-1,"STATE":-1,"ADDRESS":-1};
//        JSONObject jsStr = JSONObject.fromObject(str);
//        String s = jsStr.get("USERNAME").toString();
//        System.out.println(a);//{"c":"d","a":"b"}System.out.println(a.get("c"));//d}

        String bankCode = "JS";
        if(!bankCode.equals(PayUtil.JSNX_CREDIT)&&!bankCode.equals(PayUtil.JSNX_DEBIT)){
            String url = ParameterUtil.URL_SESSION +
                    ParameterUtil.URL_WEB +
                    ParameterUtil.REFUND_URL +
                    "?outTradeNo="+1+"&money="+1+"&st=c&openId="+1;
           System.out.println(url);
        }else {
            System.out.println(1);
        }


        System.out.println(System.currentTimeMillis() / 1000);
        long date = System.currentTimeMillis() / 1000;
        long endDate = 1538302691;
        if(date>endDate){
            System.out.println(1);
        }else{
            System.out.println(2);
        }


    }
}
