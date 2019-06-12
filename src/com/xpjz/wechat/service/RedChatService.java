package com.xpjz.wechat.service;

import com.alibaba.druid.util.StringUtils;
import com.xpjz.wechat.utils.*;
import com.xpjz.wechat.utils.redis.JedisClientSingle;
import com.xpjz.wechat.wxpay.pay.MyConfig;
import com.xpjz.wechat.wxpay.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyuping on 2018/8/2.
 */
@Service
@Transactional(
        readOnly = true
)
public class RedChatService {


    private static Logger log = LoggerFactory.getLogger(RedChatService.class);

    @Autowired
    private JedisClientSingle jedisClientSingle;


    /**
     * 创建红包
     * @param money
     * @param sendname
     * @param outTradeNo
     * @param openId
     * @return
     * @throws Exception
     */
    public Map<String,String> createRed(String money,
                                            String sendname,
                                            String outTradeNo,
                                            String openId) throws Exception{
            Map<String,String> requsetMap = new HashMap<>();

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
                String title = PropertyUtil.get("RED_TEXT");          //红包活动名称 
                String name = sendname;               //红包发送方名称
                String wishing = PropertyUtil.get("RED_WISHING");        //红包祝福语
                // 回调地址 红包页面
                String rurl = ParameterUtil.URL_SESSION+ParameterUtil.URL_WEB+"/red/redirectUrl?openId="+openId;
                url = url+"?uid="+uid+"&type="+type+
                        "&money="+creatMoney+
                        "&orderid="+orderid+"&reqtick="+reqtick+
                        "&sign="+sign+"&title="+title+
                        "&name="+name+"&wishing="+wishing+
                        "&rurl="+rurl;
                String responseContent = HttpUtil.getHttp(url);
                Map<String,String> map = new HashMap<String,String>();
                map.put("data",responseContent);
                net.sf.json.JSONObject jsStr = net.sf.json.JSONObject.fromObject(responseContent);
                String ticket = jsStr.get("ticket").toString();
                requsetMap.put("ticket",ticket);
                requsetMap.put("outTradeNo",outTradeNo);
                log.info("****************创建红包 start********************");
                log.info("openId >>>>"+openId);
                log.info("ticket >>>> "+ticket);
                log.info("money >>>> +"+money);
                log.info("outTradeNo >>>> +"+outTradeNo);
                log.info("红包创建时间 >>>>>" + new Date());
                log.info("outTradeNo >>>> +成功");
                log.info("****************创建红包 end********************");
                jedisClientSingle.set(outTradeNo+"_1","ok");
                jedisClientSingle.set(outTradeNo+"_ticket",ticket);
                return requsetMap;
        }



    public String getRed(String ticket,String outTradeNo){
                    String ticket1 = jedisClientSingle.get(outTradeNo+"_ticket");
                    log.info("**********发送红包 start************");
                    log.info("outTradeNo >>>>>" + outTradeNo);
                    log.info("ticket >>>>>" + ticket);
                    log.info("jedisClientSingle.get >>>>>" + ticket1);
                    log.info("红包发放时间 >>>>>" + new Date());
                    if(StringUtils.isEmpty(ticket1)){
                        jedisClientSingle.set("ticket_num","");
                    }
                    //创建红包接口
                    String url = MyConfig.GET_RED;
                    //参数
                    url = url + "?uid=" + MyConfig.UID + "&ticket=" + ticket1;
                    String andViewUrl = "redirect:" + url;
                    log.info("路径转发：" + url);
                    log.info("**********发送红包 end************");
                    return andViewUrl;


        }




}

