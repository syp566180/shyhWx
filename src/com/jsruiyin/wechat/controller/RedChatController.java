package com.jsruiyin.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.jsruiyin.common.utils.Md5Encrypt;
import com.jsruiyin.wechat.service.RedChatService;
import com.jsruiyin.wechat.thread.TokenThread;
import com.jsruiyin.wechat.utils.*;
import com.jsruiyin.wechat.utils.redis.JedisClientSingle;
import com.jsruiyin.wechat.wxpay.pay.MyConfig;

import com.jsruiyin.wechat.wxpay.sdk.WXPayUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;




import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.provider.MD5;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by chenyuping on 2018/8/2.
 */

@Controller
@RequestMapping({"/red"})
public class RedChatController extends BaseController{
    private static Logger log = LoggerFactory.getLogger(RedChatController.class);

    @Autowired
    private RedChatService redChatService;

    @Autowired
    private JedisClientSingle jedisClientSingle;

    /*修改状态*/
    @RequestMapping({"/updateStatus"})
    @ResponseBody
    public String updateStatus(String outTradeNo){
        jedisClientSingle.set(outTradeNo+"_1","ok");
        return "0";
    }

    @RequestMapping({"/createRed"})
    @ResponseBody
    public Map<String,String> createRed(
                            String money,
                            String sendname,String outTradeNo,String openId
                            ) throws Exception{
            Map<String,String> requsetMap = redChatService.createRed(money,sendname,outTradeNo,openId);
            return requsetMap;
    }


    @RequestMapping({"/redirectUrl"})
    public String redirectUrl(String openId){
        String url = WeixinUtil.replaceUrl(openId);
        return "redirect:" + url;
    }




    @RequestMapping({"/getRed"})
    public ModelAndView getRed(String ticket,String outTradeNo) throws Exception{
           String andViewUrl = redChatService.getRed(ticket,outTradeNo);
            return new ModelAndView(andViewUrl);
    }



    /**
     * 测试红包
     */
    @RequestMapping({"/redTest"})
    public Map<String,String> redTest(String money){
        String url= MyConfig.CREATE_RED;
        String apikey = MyConfig.API_KEY;
        String uid = MyConfig.UID; //客户编码
        String type = "0"; //0:红包接口 1.企业付款接口  
        String creatMoney = WeixinUtil.getMoney(money); //金额
        String orderid = WeixinUtil.generateOrderSN(); //订单号
        long timeStamp = WXPayUtil.getCurrentTimestamp();//当前时间戳
        String reqtick = String.valueOf(timeStamp); //请求时间戳 
        String md5 = uid+type+orderid+creatMoney+reqtick+apikey;
        //System.out.println(md5);
        //md5加密
        String sign = MD5Util.MD5(md5);
        System.out.println(sign);
        //String sign = MD5sign(map,apikey); //签名 md5(uid+type+orderid+money+reqtick+apikey)
        String title = "中秋大红包";          //红包活动名称 
        String name = "1111";           //红包发送方名称
        String wishing = "中秋大红包";        //红包祝福语
        String rurl = "https://shwx.huhuschool.com/wx/festival/html/share.html";                //回调地址 红包页面
        url = url+"?uid="+uid+"&type="+type+"&money="+creatMoney+"&orderid="+orderid+"&reqtick="+reqtick+"&sign="+sign+"&title="+title+"&name="+name+"&wishing="+wishing+"&rurl="+rurl;
        System.out.println("创建红包路径.."+url);
        String responseContent = HttpUtil.getHttp(url);
        Map<String,String> map = new HashMap<String,String>();
        map.put("data",responseContent);
        net.sf.json.JSONObject jsStr = net.sf.json.JSONObject.fromObject(responseContent);
        String ticket = jsStr.get("ticket").toString();
        System.out.println("ticket      == "+ticket);
        String ur_= MyConfig.GET_RED;
        //参数
        ur_ = ur_+"?uid="+uid+"&ticket="+ticket;
        System.out.println("领取红包路径："+ur_);
        Map<String,String>  map1 = new HashMap<>();
        map1.put("msg","成功");
        map1.put("data",ur_);
        return map1;
    }


}
