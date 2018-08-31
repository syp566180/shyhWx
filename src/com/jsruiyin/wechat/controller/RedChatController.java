package com.jsruiyin.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.jsruiyin.common.utils.Md5Encrypt;
import com.jsruiyin.wechat.thread.TokenThread;
import com.jsruiyin.wechat.utils.MD5Util;
import com.jsruiyin.wechat.utils.ParameterUtil;
import com.jsruiyin.wechat.utils.PayUtil;
import com.jsruiyin.wechat.utils.WeixinUtil;
import com.jsruiyin.wechat.utils.redis.JedisClientSingle;
import com.jsruiyin.wechat.wxpay.pay.MyConfig;

import com.jsruiyin.wechat.wxpay.sdk.WXPayUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @Autowired
//    private RedChatService redChatService;
    public static final String sign_type="MD5";

    @Autowired
    private JedisClientSingle jedisClientSingle;

    @RequestMapping({"/etRed"})
    public String getRed(HttpServletRequest request){
        //方法 生成xml
        //redChatService.getXml(request);
        return null;
    }


    @RequestMapping({"/createRed"})
    public String createRed(HttpServletRequest request,
                            String money,
                            String sendname,String outTradeNo,
                            Model model,RedirectAttributes attr
                            ) throws Exception{
        //创建红包接口
        String url= MyConfig.CREATE_RED;
        //参数
        String apikey = MyConfig.API_KEY;
        String uid = MyConfig.UID; //客户编码
        Double d = Double.valueOf(money);
        String type = "0"; //0:红包接口 1.企业付款接口  
        if(d>200){
            type = "1";
        }
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
        String title = "七夕红包";          //红包活动名称 
        String name = sendname;           //红包发送方名称
        String wishing = "七夕红包";        //红包祝福语
        String rurl = "http://shwx.huhuschool.com"+ParameterUtil.URL_PAYMENT+"/html/hongbao.html";                 //回调地址 红包页面
        url = url+"?uid="+uid+"&type="+type+"&money="+creatMoney+"&orderid="+orderid+"&reqtick="+reqtick+"&sign="+sign+"&title="+title+"&name="+name+"&wishing="+wishing+"&rurl="+rurl;
        System.out.println("创建红包路径.."+url);
                    String responseContent = PayUtil.getHttp(url);
                   Map<String,String> map = new HashMap<>();
                   map.put("data",responseContent);
                   net.sf.json.JSONObject jsStr = net.sf.json.JSONObject.fromObject(responseContent);
                String ticket = jsStr.get("ticket").toString();
                System.out.println("ticket      == "+ticket);
                model.addAttribute("ticket",ticket);
                attr.addFlashAttribute("ticket", ticket);
                return "redirect:/red/getRed?ticket="+ticket+"&outTradeNo="+outTradeNo;
    }





    @RequestMapping({"/getRed"})
    public ModelAndView getRed(String ticket,String outTradeNo) throws Exception{
        //发送红包
        System.out.println("发送红包");
        //创建红包接口
        String url= MyConfig.GET_RED;
        //参数
        String uid = MyConfig.UID; //客户编码
        url = url+"?uid="+uid+"&ticket="+ticket;
        System.out.println("发送红包路径.."+url);
        //String responseContent = PayUtil.getHttp(url);
        //System.out.println("结果："+responseContent);
           String andViewUrl = "redirect:"+url;
        System.out.println("重定向页面；"+andViewUrl);
        jedisClientSingle.set(outTradeNo,"ok");
        return new ModelAndView(andViewUrl);
    }



//
//    public String getHttp(String url){
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpGet get = new HttpGet(url);
//        String responseContent = null; // 响应内容
//        CloseableHttpResponse response= null;
//        try {
//            response = client.execute(get);
//            HttpEntity entity = response.getEntity();// 响应体
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 正确返回
//                responseContent = EntityUtils.toString(entity, "UTF-8");
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (response != null)
//                    response.close();
//                if (client != null)
//                    client.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("结果："+responseContent);
//        return responseContent;
//    }









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
        if (signFields != null) {
            Arrays.sort(signFields); // 对key按照 字典顺序排序
        }

        StringBuffer signSrc = new StringBuffer("");
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



    public static void main(String[] args) {

        Map<String ,Object> map=new HashMap<String,Object>();
        map.put("uid","2222");
        map.put("type","2222");
        map.put("orderid","222");
        map.put("money","2222");
        map.put("reqtick","2222");
        /***MD5签名与验签**/
        String key="nynkFS8keZDtGnvM54OcqhHDkgE5j96G";
        String sign= MD5sign(map,key);
        System.out.println("生成的MD5签名："+sign);
        vlidateMD5sign(map, key, sign) ;


    }


    /**
     * 测试红包
     */
    @RequestMapping({"/redTest"})
    public void redTest(){
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
        String rurl = "http://www.syp666.cn"+ParameterUtil.URL_PAYMENT+"/html/hongbao.html";                 //回调地址 红包页面
        url = url+"?uid="+uid+"&type="+type+"&money="+creatMoney+"&orderid="+orderid+"&reqtick="+reqtick+"&sign="+sign+"&title="+title+"&name="+name+"&wishing="+wishing+"&rurl="+rurl;
        System.out.println("创建红包路径.."+url);
        String responseContent = PayUtil.getHttp(url);
        Map<String,String> map = new HashMap<>();
        map.put("data",responseContent);
        net.sf.json.JSONObject jsStr = net.sf.json.JSONObject.fromObject(responseContent);
        String ticket = jsStr.get("ticket").toString();
        System.out.println("ticket      == "+ticket);

        String ur_= MyConfig.GET_RED;
        //参数
        ur_ = ur_+"?uid="+uid+"&ticket="+ticket;
        System.out.println("领取红包路径："+ur_);
    }


    /**
     * 微信分享
     * @return
     */
    @RequestMapping({"/fenTest"})
    @ResponseBody
    public Map<String,String> fenTest(String url) throws Exception{
            System.out.println("进入分享");
            Map<String,String> ret = new HashMap<>();
            String AppId = ParameterUtil.APP_ID;
            long timeStamp = WXPayUtil.getCurrentTimestamp();//当前时间戳
            String timestamp = String.valueOf(timeStamp); //请求时间戳 
            String nonceStr = WXPayUtil.MD5(WXPayUtil.generateNonceStr());
            String jsApiTicket = jedisClientSingle.get(TokenThread.jsApiTicket_xw); //
            String string1;
            String signature = "";
            //注意这里参数名必须全部小写，且必须有序
            string1 = "jsapi_ticket=" + jsApiTicket +
                    "&noncestr=" + nonceStr +
                    "&timestamp=" + timestamp +
                    "&url=" + url;
            System.out.println("String1=====>"+string1);
            //签名
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = WeixinUtil.byteToHex(crypt.digest());
            System.out.println("signature=====>"+signature);
        }
        catch (NoSuchAlgorithmException e)
        {
            ret.put("success","1");
            System.out.println("WeChatController.makeWXTicket=====Start");
            System.out.println(e.getMessage());
            System.out.println("WeChatController.makeWXTicket=====End");
            return ret;
        }
        catch (UnsupportedEncodingException e)
        {
            ret.put("success","2");
            System.out.println("WeChatController.makeWXTicket=====Start");
            System.out.println(e.getMessage());
            System.out.println("WeChatController.makeWXTicket=====End");
            return ret;
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsApiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appid", AppId);
        ret.put("success","0");
        return ret;
    }









}
