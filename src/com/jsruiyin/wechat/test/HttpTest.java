package com.jsruiyin.wechat.test;

import com.jsruiyin.wechat.utils.HttpUtil;
import com.jsruiyin.wechat.utils.MD5Util;
import com.jsruiyin.wechat.utils.PayUtil;
import com.jsruiyin.wechat.utils.WeixinUtil;
import com.jsruiyin.wechat.wxpay.pay.MyConfig;
import com.jsruiyin.wechat.wxpay.sdk.WXPayUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyuping on 2018/8/12.
 */
public class HttpTest {


    public static void main(String[] args) throws Exception{
        String strTest = "我是";
        System.out.println(strTest);
        strTest = URLEncoder.encode(strTest, "UTF-8");//转码
        System.out.println(strTest);
        strTest = URLDecoder.decode(strTest,"UTF-8");//解码
        System.out.println(strTest);

    }


    public static void red(){
        String url= MyConfig.CREATE_RED;
        String apikey = MyConfig.API_KEY;
        String uid = MyConfig.UID; //客户编码
        String type = "0"; //0:红包接口 1.企业付款接口  
        String creatMoney = WeixinUtil.getMoney("0.4"); //金额
        String orderid = WeixinUtil.generateOrderSN(); //订单号
        long timeStamp = WXPayUtil.getCurrentTimestamp();//当前时间戳
        String reqtick = String.valueOf(timeStamp); //请求时间戳 
        String md5 = uid+type+orderid+creatMoney+reqtick+apikey;
        //System.out.println(md5);
        //md5加密
        String sign = MD5Util.MD5(md5);
        System.out.println(sign);
        //String sign = MD5sign(map,apikey); //签名 md5(uid+type+orderid+money+reqtick+apikey)
        String title = "七夕红包";          //红包活动名称 
        String name = "1111";           //红包发送方名称
        String wishing = "七夕红包";        //红包祝福语
        String rurl = "http://shwx.huhuschool.com/payment/html/hongbao.html";                 //回调地址 红包页面
        url = url+"?uid="+uid+"&type="+type+"&money="+creatMoney+"&orderid="+orderid+"&reqtick="+reqtick+"&sign="+sign+"&title="+title+"&name="+name+"&wishing="+wishing+"&rurl="+rurl;
        System.out.println("创建红包路径.."+url);
        String responseContent = HttpUtil.getHttp(url);
        Map<String,String> map = new HashMap<>();
        map.put("data",responseContent);
        net.sf.json.JSONObject jsStr = net.sf.json.JSONObject.fromObject(responseContent);
        String ticket = jsStr.get("ticket").toString();
        System.out.println("ticket      == "+ticket);

        String ur_= MyConfig.GET_RED;
        //参数
        url = url+"?uid="+uid+"&ticket="+ticket;
        System.out.println("领取红包路径："+ur_);
    }

}
