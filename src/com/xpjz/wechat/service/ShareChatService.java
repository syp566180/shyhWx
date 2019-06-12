package com.xpjz.wechat.service;

import com.xpjz.wechat.utils.ParameterUtil;
import com.xpjz.wechat.utils.PropertyUtil;
import com.xpjz.wechat.utils.WeixinUtil;
import com.xpjz.wechat.utils.redis.JedisClientSingle;
import com.xpjz.wechat.wxpay.sdk.WXPayUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syp on 2018/9/26.
 */
@Service
@Transactional(readOnly = true)
public class ShareChatService {

    private static Logger log = LoggerFactory.getLogger(ShareChatService.class);

    @Autowired
    private JedisClientSingle jedisClientSingle;

    /**
     * 分享
     * @param url
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public Map<String,String> partake(String url) throws Exception{
        url = StringEscapeUtils.unescapeHtml(url);
        Map<String,String> ret = new HashMap<>();
        String AppId = ParameterUtil.APP_ID;
        long timeStamp = WXPayUtil.getCurrentTimestamp();//当前时间戳
        String timestamp = String.valueOf(timeStamp); //请求时间戳 
        String nonceStr = WXPayUtil.MD5(WXPayUtil.generateNonceStr());
        String jsApiTicket = jedisClientSingle.get(PropertyUtil.get("jsApiTicketRedis")); //
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        //签名
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = WeixinUtil.byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            ret.put("success","1");
            log.info("WeChatController.makeWXTicket=====Start");
            log.info(e.getMessage());
            log.info("WeChatController.makeWXTicket=====End");
            return ret;
        }
        catch (UnsupportedEncodingException e)
        {
            ret.put("success","2");
            log.info("WeChatController.makeWXTicket=====Start");
            log.info(e.getMessage());
            log.info("WeChatController.makeWXTicket=====End");
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
