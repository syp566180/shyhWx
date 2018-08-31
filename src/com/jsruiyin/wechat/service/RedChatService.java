package com.jsruiyin.wechat.service;

import com.jsruiyin.wechat.utils.WeixinUtil;
import com.jsruiyin.wechat.wxpay.pay.MyConfig;
import com.jsruiyin.wechat.wxpay.pay.RedOrder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyuping on 2018/8/2.
 */
@Service
public class RedChatService {


    public String getXml(HttpServletRequest request) throws Exception{
        RedOrder redOrder = new RedOrder();
        MyConfig config = new MyConfig();
        String xml = "";
        Map<String,Object> map = new HashMap<>();
        //随机字符串 nonce_str
        map.put("nonce_str",redOrder.getNonce_str());
        //商户订单号
        map.put("mch_billno", WeixinUtil.generateOrderSN());
        //商户号
        map.put("mch_id",config.getMchID());
        //公众号appid
        map.put("wxappid",config.getAppID());
        //商户名称
        map.put("send_name","江苏瑞银");
        //openid re_openid
        map.put("re_openid","江苏瑞银");
        //付款金额 total_amount
        map.put("total_amount",0.01);
        //红包发放总人数 total_num
        map.put("total_num",1);
        //红包祝福语 wishing
        map.put("wishing","中奖");
        //Ip地址 client_ip
        map.put("send_name",request.getRemoteAddr());
        //活动名称 act_name
        map.put("send_name","中奖红包");
        //备注 remark
        map.put("send_name","6666666");
        //签名
        //map.put("sign",);



        return xml;
    }
}
