package com.jsruiyin.wechat.service;

import com.alibaba.druid.util.StringUtils;
import com.jsruiyin.wechat.dao.PayDao;
import com.jsruiyin.wechat.entity.user.User;
import com.jsruiyin.wechat.utils.*;
import com.jsruiyin.wechat.utils.http.HttpClientConnectionManager;
import com.jsruiyin.wechat.utils.redis.JedisClientSingle;
import com.jsruiyin.wechat.wxpay.pay.MyConfig;
import com.jsruiyin.wechat.wxpay.pay.UnifiedOrder;
import com.jsruiyin.wechat.wxpay.sdk.WXPay;
import com.jsruiyin.wechat.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;


/**
 * Created by chenyuping on 2018/8/1.
 */
@Service("payChatService")
public class PayChatService{

    private static Logger log = LoggerFactory.getLogger(PayChatService.class);

    @Autowired
    private JedisClientSingle jedisClientSingle;

    public String index(String outTradeNo,String money,String openId,String name) throws Exception{
        String url = null;
        /** 获取存款银行编码 **/
        String bankCode = jedisClientSingle.get(openId+"_"+outTradeNo+"_bank");
        jedisClientSingle.set(outTradeNo,"ok");
        /** 微信昵称 **/
        name = "微信用户**";
        name = URLDecoder.decode(name,"UTF-8");//解码
        name = URLEncoder.encode(name, "UTF-8");//转码

        if(!StringUtils.isEmpty(bankCode)) {
            /** 是否测试银行编码 **/
            if (new PayUtil().getIsBankCode()) {
                url = refundIsBankCodeJSNX(bankCode,outTradeNo,money,openId);
                if (!StringUtils.isEmpty(url)) {
                    log.info("退款路径...{}", url);
                    return url;
                } else {
                    url = ParameterUtil.URL_SESSION +
                            ParameterUtil.URL_WX +
                            ParameterUtil.URL_PAYMENT +
                            ParameterUtil.URL_PAY_INDEX +
                            "?openId=" +
                            openId +
                            "&outTradeNo=" +
                            outTradeNo;

                    log.info("支付成功后跳转路径....start");
                    log.info(url);
                    log.info("支付成功后跳转路径....end");


                }
            }
        }
            return url;
    }



    public Map<String,String> requestData(String Url,
                                          String data
                                             ) throws Exception{
        //data = "{\"msg\"=\"恭喜中奖！\", \"prizeAmount\"=1.88, \"drawCount\"=\"2\", \"status\"=\"1\"}";
        JSONObject jsonObject = JSONObject.fromObject(data);
        Url = WeixinUtil.getUrl(Url,jsonObject);
        Map<String,String> map = new HashMap<>();
        String req = HttpUtil.getHttp(Url);
        map.put("map",req);
        return map;
    }




    public String payHtml(String openId,String state,String nickname) throws Exception{
        state = WeixinUtil.generateOrderSN();
        //name = "微信用户**";
        nickname = URLDecoder.decode(nickname,"UTF-8");//解码
        nickname = URLEncoder.encode(nickname, "UTF-8");//转码
        String url = ParameterUtil.URL_SESSION +
                ParameterUtil.URL_WX +
                ParameterUtil.URL_PAY +
                "?openId=" + openId + "&state=" + state +"&name=" + nickname;
        return url;
    }


    public Map<String,String> jsApi(String payMoney,
                                    String openId,
                                    String state,
                                    HttpServletRequest request
                                    ) throws Exception {
        /**  调用接口 微信sdk接口 **/
        MyConfig config = new MyConfig();
        /** 生成订单 订单类 **/
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        /** 公众号appid **/
        unifiedOrder.setAppid(config.getAppID());
        /** APPSECRET **/
        unifiedOrder.setAppsecret(ParameterUtil.APPSECRET);
        /** 商户号 **/
        unifiedOrder.setMch_id(config.getMchID());
        /** key值 **/
        unifiedOrder.setMch_key(config.getKey());
        /** ------1.获取参数信息------- */
        /** 商户订单号 **/
        String out_trade_no = state;
        unifiedOrder.setOut_trade_no(out_trade_no);
        /** 价格 **/
        String money = WeixinUtil.getMoney(payMoney);
        unifiedOrder.setTotal_fee(money);
        /** 用户openid **/
        unifiedOrder.setOpenid(openId);
        /** ------3.生成预支付订单需要的的package数据------- */
        /** 随机数 MD5加密 **/
        String nonce_str = WXPayUtil.MD5(WXPayUtil.generateNonceStr());
        unifiedOrder.setNonce_str(nonce_str);
        /** 交易类型 ：jsapi代表微信公众号支付 **/
        unifiedOrder.setTrade_type(config.TRADE_TYPE_JSAPI);
        /** 订单生成的机器 IP  **/
        String spbill_create_ip = request.getRemoteAddr();
        unifiedOrder.setSpbill_create_ip(spbill_create_ip);
        /** 回调页面 **/
        unifiedOrder.setNotify_url(ParameterUtil.NOTIFY_URL);
        /** 组装  **/
        SortedMap<String, String> st = WeixinUtil.getPackage(unifiedOrder);
        /** 生成带签名的xml **/
        String xml = WXPayUtil.generateSignedXml(st,ParameterUtil.API_PASSWORD);

        Map<String,String> map = new HashMap<>();
        /**------ 调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder 生产预支付订单----------**/
        try {
            String res = HttpUtil.httpsRequest(MyConfig.UNIFIED_ORDER_URL,"POST",xml);
            Map<String,String> resp = WXPayUtil.xmlToMap(res);
            //结果
            log.info("结果:"+resp);
            String return_code = resp.get("return_code");
            String result_code = resp.get("result_code");
            String ok = jedisClientSingle.get(out_trade_no);
            if(ok != null) {
                if (ok.equals("ok")) {
                    map.put("success", "4");
                    String code = jedisClientSingle.get(out_trade_no+"_1");
                    if(com.alibaba.druid.util.StringUtils.isEmpty(code)){
                        map.put("requestCode","0");
                    }else{
                        map.put("requestCode","1");
                    }
                    return map;
                }
            }
            if(ParameterUtil.FAIL.equals(result_code)){
                String err_code = resp.get("err_code");
                if(ParameterUtil.ORDERPAID.equals(err_code)){
                    map.put("openid",unifiedOrder.getOpenid());
                    map.put("outTradeNo",out_trade_no);//订单编号
                    map.put("money",payMoney);
                    map.put("success","1");
                    return map;
                }
                if(ParameterUtil.SYSTEMERROR.equals(err_code)){
                    map.put("success","2");
                    map.put("msg","系统繁忙，请稍后再试!");
                    return map;
                }
                map.put("msg",err_code);
                map.put("success","3");
                return map;//支付下单失败，重定向至订单列表

            }
            if(ParameterUtil.SUCCESS.equals(return_code)&&ParameterUtil.SUCCESS.equals(result_code)){
                log.info("**************进入统一下单*************");
                String appId = resp.get("appid");//微信公众号AppId
                long timeStamp = WXPayUtil.getCurrentTimestamp();//当前时间戳
                String prepayId = "prepay_id="+resp.get("prepay_id");//统一下单返回的预支付id
                nonce_str = WXPayUtil.MD5(WXPayUtil.generateNonceStr()); //随机数
                Map<String,String> m = new HashMap<>();
                m.put("appId",appId);
                m.put("package",prepayId);
                m.put("timeStamp",String.valueOf(timeStamp));
                m.put("nonceStr",nonce_str);
                m.put("signType","MD5");
                //获取签名
                String sign = WeixinUtil.generateSignature(m,ParameterUtil.API_PASSWORD);
                //返回页面
                map.put("openid",unifiedOrder.getOpenid());
                map.put("appId",appId);
                map.put("timeStamp",String.valueOf(timeStamp));
                map.put("nonceStr",nonce_str);
                map.put("prepayId",prepayId);
                map.put("paySign",sign);//获取签名
                map.put("outTradeNo",out_trade_no);//订单编号
                map.put("money",payMoney);
                map.put("success","0");
                log.info(map.toString());
                log.info("**************结束统一下单*************");
            }else{
                log.error("微信统一下单失败,订单编号:"+unifiedOrder.getOut_trade_no()+",失败原因:"+resp.get("err_code_des"));
                map.put("msg","微信统一下单失败,订单编号:"+unifiedOrder.getOut_trade_no()+",失败原因:"+resp.get("err_code_des"));
                map.put("success","3");
                return map;//支付下单失败，重定向至订单列表
            }
        } catch (Exception e) {
            log.error("统一支付接口获取预支付订单出错", e);
            return map;//支付下单失败，重定向至订单列表
        }
        return map;
    }



    public String weixinNotify(HttpServletRequest request) throws Exception{
        String result;//返回给微信的处理结果
        String inputLine;
        StringBuffer notityXml = new StringBuffer();
        request.setCharacterEncoding("UTF-8");
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml.append(inputLine);
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
            result = WeixinUtil.setXML("fail","xml获取失败");
            return result;
        }
        if (org.apache.commons.lang.StringUtils.isEmpty(notityXml.toString())) {
            result = WeixinUtil.setXML("fail","xml为空");
            return result;
        }
        Map<String,String> resultMap = WXPayUtil.xmlToMap(notityXml.toString());
        // 解析各种数据
//            String appid =resultMap.get("appid");//应用ID
//            String attach = resultMap.get("attach");//商家数据包
        String bank_type = resultMap.get("bank_type");//付款银行
//            String cash_fee = resultMap.get("cash_fee");//现金支付金额
//            String fee_type = resultMap.get("fee_type");//货币种类
//            String is_subscribe = resultMap.get("is_subscribe");//是否关注公众账号
//            String mch_id = resultMap.get("mch_id");//商户号
//            String nonce_str = resultMap.get("nonce_str");//随机字符串
        String openid = resultMap.get("openid");//用户标识
        String out_trade_no = resultMap.get("out_trade_no");// 获取商户订单号
        String result_code = resultMap.get("result_code");// 业务结果
        String return_code = resultMap.get("return_code");// SUCCESS/FAIL
        String sign = resultMap.get("sign");// 获取签名
        String time_end = resultMap.get("time_end");//支付完成时间
        String total_fee = resultMap.get("total_fee");// 获取订单金额
        String trade_type = resultMap.get("trade_type");//交易类型
        String transaction_id = resultMap.get("transaction_id");//微信支付订单号
        //通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了.
    //    log.info("**************************************************************************************************");
//            System.out.println(appid+"-------------------应用ID");
//            System.out.println(attach+"-------------------商家数据包");
//            System.out.println(bank_type+"-------------------付款银行");
//            System.out.println(cash_fee+"-------------------现金支付金额");
//            System.out.println(fee_type+"-------------------货币种类");
//            System.out.println(is_subscribe+"-------------------是否关注公众账号");
//            System.out.println(mch_id+"-------------------商户号");
//            System.out.println(nonce_str+"-------------------随机字符串");
//            System.out.println(openid+"-------------------用户标识");
//        log.info(out_trade_no+"-------------------获取商户订单号");
//        log.info(result_code+"-------------------业务结果");
//        log.info(return_code+"------------------- SUCCESS/FAIL");
//        log.info(sign+"-------------------获取签名-微信回调的签名");
//        log.info(time_end+"-------------------支付完成时间");
//        log.info(total_fee+"-------------------获取订单金额");
    //    log.info(trade_type+"-------------------交易类型");
//        log.info(transaction_id+"-------------------微信支付订单号");
       // log.info("**************************************************************************************************");
        if (result_code.equals(ParameterUtil.SUCCESS)) {
            result = WeixinUtil.setXML("SUCCESS", "OK");
            jedisClientSingle.set(openid+"_"+out_trade_no+"_bank",bank_type);
            return result;
        }else{
            result = WeixinUtil.setXML("fail", "微信返回的交易状态不正确（result_code=" + result_code + "）");
            log.info("微信返回的交易状态不正确（result_code=" +result_code+ "）");
            return result;
        }
    }

    /**
     *
     * @param outTradeNo
     * @param money
     * @param st
     * @param openId
     * @return
     * @throws Exception
     * a:表示支付code校验不通过，退款返回首页
     * b.表示直接退回首页，没有退款步骤
     */
    public String reimburse(
            String outTradeNo,
            String money,
            String st,
            String openId) throws Exception{
        log.info("退款接口申请！！");
        log.info("*************************************************");
        log.info("订单号：{},金额：{},类型：{},用户id：{}",outTradeNo,money,st,openId);
        log.info("*************************************************");
        /**返回首页 url **/
        String url = WeixinUtil.replaceUrl(openId);
        if(st.equals("b")){
            return url;
        }
        //微信退款信息
        String appId = ParameterUtil.APP_ID;
        String mch_id = ParameterUtil.BUSINESS_NUMBER;
        String nonce_str = WXPayUtil.MD5(WXPayUtil.generateNonceStr());
        String out_trade_no = outTradeNo;
        String out_refund_no = WeixinUtil.generateOrderSN();
        String total_fee = money;
        String refund_fee = money;
        Map<String,String> result = wxRefund2(appId,mch_id,nonce_str,out_trade_no,out_refund_no,total_fee,refund_fee);
        String code = result.get("returncode");
        if(code.equals("ok")){
            //返回第一个页面
            if(st.equals("a")){
                return url;
            }else if(st.equals("c")){
                log.info("支付金额方式不对！{}",st);
                url = ParameterUtil.URL_SESSION +
                        ParameterUtil.URL_WX +
                        ParameterUtil.URL_PAYMENT +
                        ParameterUtil.URL_PAY_BACK +
                        "?openId=" +
                        openId;
                log.info("退款跳转地址：{}",url);
            }
        }
        return  url;
    }





    //退款方法2
    private Map<String,String>  wxRefund2(String appId,
                                          String mch_id,
                                          String nonce_str,
                                          String out_trade_no,
                                          String out_refund_no,
                                          String total_fee,
                                          String refund_fee) throws Exception{
        //调用接口 微信sdk接口
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        Map<String,String> result=new HashMap<>();
        //生成签名
        Map<String,String> packageParams = new HashMap();
        packageParams.put("appid",appId);
        packageParams.put("mch_id",mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("out_trade_no",out_trade_no);
        packageParams.put("out_refund_no",out_refund_no);
        packageParams.put("total_fee", AmountUtils.changeY2F(total_fee));
        packageParams.put("refund_fee", AmountUtils.changeY2F(refund_fee));
        //

        //获取签名
        String sign = WeixinUtil.generateSignature(packageParams,ParameterUtil.API_PASSWORD);
        packageParams.put("sign",sign);

        //map转xml
        String xml = WXPayUtil.mapToXml(packageParams);


        // try {
             /*--------4.读取证书文件,这一段是直接从微信支付平台提供的demo中copy的，所以一般不需要修改------*/
        String path = MyConfig.CERT_PATH;
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(path));
        try {
            keyStore.load(instream, mch_id.toCharArray());
        } finally {
            instream.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            /*------5.发送数据到微信的退款接口------- */
        String url="https://api.mch.weixin.qq.com/secapi/pay/refund";
        HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
        httpost.setEntity(new StringEntity(xml, "UTF-8"));
        HttpResponse weixinResponse = httpClient.execute(httpost);
        String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
        log.info(jsonStr);

        Map<String,String> map = WXPayUtil.xmlToMap(jsonStr);

        log.info("*********************退款申请接口 start*************************");
        log.info("数据",map);
        log.info("*********************退款申请接口 end*************************");
        if("success".equalsIgnoreCase(map.get("return_code"))){
            log.info("退款成功");
            result.put("returncode", "ok");
            result.put("returninfo", "退款成功");
        }else{
            log.info("退款失败");
            result.put("returncode", "error");
            result.put("returninfo", "退款失败");
        }
        return result;
    }


    public String refundIsBankCodeJSNX(String bankCode,String outTradeNo,String money,String openId){
        log.info("bankCode....{}",bankCode);
        String url = null;
        if(!bankCode.equals(PayUtil.JSNX_CREDIT)&&!bankCode.equals(PayUtil.JSNX_DEBIT)){
            url = getBankCode(outTradeNo,money,openId);
            log.info("走退款路口....bankCode:{}",bankCode);
            log.info("走退款路口....openId:{}",openId);
            log.info("走退款路口....url:{}",url);
        }
        return url;
    }

    public String getBankCode(String outTradeNo,String money,String openId){
        String url = ParameterUtil.URL_SESSION +
                ParameterUtil.URL_WEB +
                ParameterUtil.REFUND_URL +
                "?outTradeNo="+outTradeNo+"&money="+money+"&st=c&openId="+openId;
        return url;
    }




    public String refundIsBankCode(String bankCode){
        String url = null;
        if(!bankCode.equals(PayUtil.JSNX_CREDIT)){
            url = ParameterUtil.REFUND_URL +
                    "?outTradeNo=OUTTRADENO&money=MONEY&st=a";
       }
       return url;
    }



}
