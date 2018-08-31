package com.jsruiyin.wechat.controller;

import com.jsruiyin.common.utils.IdGen;
import com.jsruiyin.wechat.entity.user.User;
import com.jsruiyin.wechat.utils.*;
import com.jsruiyin.wechat.utils.redis.JedisClientSingle;
import com.jsruiyin.wechat.wxpay.pay.MyConfig;
import com.jsruiyin.wechat.wxpay.pay.UnifiedOrder;
import com.jsruiyin.wechat.wxpay.sdk.WXPay;
import com.jsruiyin.wechat.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




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
import com.jsruiyin.wechat.utils.http.HttpClientConnectionManager;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by chenyuping on 2018/8/1.
 */
@Controller
@RequestMapping({"/pay"})
public class PayChatController extends BaseController{

    private static Logger log = LoggerFactory.getLogger(PayChatController.class);


//    // @Resource
//    private PayChatService payChatService;

    @Autowired
    private JedisClientSingle jedisClientSingle;


    /**
     * 退款返回
     */
    @RequestMapping({"/fhindex"})
    public String fhindex(){
        return "redirect:" + ParameterUtil.ACTIVITY_URL_1;
    }


    /**
     * 支付成功跳转方法
     */
    @RequestMapping({"/index"})
    public String index(String outTradeNo,String money,String openid,String name,RedirectAttributes attr) throws Exception{
        String bankType = jedisClientSingle.get(openid);
        System.out.println("name == "+name);
        name = "微信用户**";
        name = URLDecoder.decode(name,"UTF-8");//解码
        System.out.println(name);
        name = URLEncoder.encode(name, "UTF-8");//转码
        System.out.println(name);
        //测试用零钱
        if(!bankType.equals(PayUtil.JSNX_CREDIT)){
            //非领取退钱
            System.out.println("退钱入口...!!!!!");
            return "redirect:/pay/refund2?outTradeNo="+outTradeNo+"&money="+money+"&st=a";
        }
        //调接口
        String url = "http://127.0.0.1:8098/snyh/client/drawRecord/draw?openId="+openid+"&name="+name;
        String req = PayUtil.getHttp(url);
        //String data = "{\"SystemCode\":\"1\",\"data\":{\"msg\"=\"恭喜中奖！\", \"prizeAmount\"=1.88, \"drawCount\"=\"2\", \"status\"=\"1\"}}";
        JSONObject jsonObject = JSONObject.fromObject(req).getJSONObject("data");
        //状态位 status
        String status = jsonObject.getString("status");
        String msg = jsonObject.getString("msg");
        //红包金额
        if(status.equals("1")){
            //创建红包并且发送
            Double prizeAmount = jsonObject.getDouble("prizeAmount");
            String drawCount = jsonObject.getString("drawCount");
            System.out.println("prizeAmount..."+prizeAmount);
            String amount = String.valueOf(prizeAmount);
            //attr.addFlashAttribute("money", amount);
            //attr.addFlashAttribute("sendname", "幸运用户");
            return "redirect:/red/createRed?money="+amount+"&sendname=七夕幸运用户"+"&outTradeNo="+outTradeNo;
        }

        if(status.equals("3")){
            return "redirect:/pay/refund2?outTradeNo="+outTradeNo+"&money="+money+"&st=b";
        }

        if(status.equals("4")){
            return "redirect:/pay/refund2?outTradeNo="+outTradeNo+"&money="+money+"&st=c";
        }

        jedisClientSingle.set(outTradeNo,"ok");
        System.out.print("outTradeNo ..."+jedisClientSingle.get(outTradeNo));
        String noPrize = "http://shwx.huhuschool.com"+ParameterUtil.URL_PAYMENT+"/html/noprize.html";
        return "redirect:"+noPrize;
    }


    /**
     * 支付成功跳转方法
     */
    @RequestMapping({"/payhtml"})
    public String index(String openid,String state,String name) throws Exception{
//        state= WeixinUtil.generateOrderSN();
//        name = "微信用户**";
//        name = URLDecoder.decode(name,"UTF-8");//解码
//        System.out.println(name);
//        name = URLEncoder.encode(name, "UTF-8");//转码
//        System.out.println(name);
//        String url = "http://shwx.huhuschool.com/payment/html/pay.html?openid=" + openid + "&state=" + state +"&name=" + name;
//        return "redirect:" + url;
        String url = "http://shwx.huhuschool.com"+ParameterUtil.URL_PAYMENT+"/html/stop.html";
        return "redirect:" + url;
    }

    /**
     * 公众号支付订单接口
     * @param payMoney
     * @param openId
     * @param state
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping({"/jsApi"})
    @ResponseBody
    public Map<String,String> pay(String payMoney,
                      String openId,
                      String state,
                      HttpServletRequest request,
                      HttpServletResponse response,
                      Model model) throws Exception {
            System.out.println("openId    "+openId);
            //调用接口 微信sdk接口
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);
            //生成订单 订单类
            UnifiedOrder unifiedOrder = new UnifiedOrder();
            //公众号appid
            unifiedOrder.setAppid(config.getAppID());
            System.out.println("AppId  _"+config.getAppID());
            //APPSECRET
            unifiedOrder.setAppsecret(ParameterUtil.APPSECRET);
            //商户号
            unifiedOrder.setMch_id(config.getMchID());
            //key值
            unifiedOrder.setMch_key(config.getKey());
            /*------1.获取参数信息------- */
            //商户订单号
            String out_trade_no = state;
            unifiedOrder.setOut_trade_no(out_trade_no);
            //价格
            String money = WeixinUtil.getMoney(payMoney);
            unifiedOrder.setTotal_fee(money);
            //用户openid
            unifiedOrder.setOpenid(openId);
            /*------3.生成预支付订单需要的的package数据------- */
            //随机数 MD5加密
            String nonce_str = WXPayUtil.MD5(WXPayUtil.generateNonceStr());
            unifiedOrder.setNonce_str(nonce_str);
            //交易类型 ：jsapi代表微信公众号支付
            unifiedOrder.setTrade_type(config.TRADE_TYPE_JSAPI);
            //订单生成的机器 IP
            String spbill_create_ip = request.getRemoteAddr();
            unifiedOrder.setSpbill_create_ip(spbill_create_ip);
            //回调页面
            unifiedOrder.setNotify_url(ParameterUtil.NOTIFY_URL);
            //组装
            SortedMap<String, String> st = WeixinUtil.getPackage(unifiedOrder);
            //生成带签名的xml
            String xml = WXPayUtil.generateSignedXml(st,ParameterUtil.API_PASSWORD);

            Map<String,String> map = new HashMap<>();
            //判断签名是否正确
            //System.out.println("签名正确："+WXPayUtil.isSignatureValid(xml,ParameterUtil.API_PASSWORD));
            /*------ 调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder 生产预支付订单----------*/
            String prepay_id="";
            try {
                //Map<String,String> s = WXPayUtil.xmlToMap(xml);
                //System.out.println("map: "+s);
                //方法一
                //resp = wxpay.unifiedOrder(s);
                String res = HttpUtil.httpsRequest(MyConfig.UNIFIED_ORDER_URL,"POST",xml);

                //xml转map
                Map<String,String> resp = WXPayUtil.xmlToMap(res);
                //{nonce_str=5kHGWt4936IYEBim, appid=wx1a4e3c5b7c5c1619,
                // sign=715A667BD894CB2F1230247D90312CCA, trade_type=JSAPI,
                // return_msg=OK, result_code=SUCCESS, mch_id=1466639502,
                // return_code=SUCCESS, prepay_id=wx01212306982246a2cee9b1c31882863856}
                //结果
                System.out.println("结果:"+resp);
                /*将prepay_id存到数据库中，先暂存redis里面*/
                prepay_id = resp.get("prepay_id");
                 /*------将预支付订单的id和其他信息生成签名并一起返回到jsp页面 ------- */
                String return_code = resp.get("return_code");
                String result_code = resp.get("result_code");

                String ok = jedisClientSingle.get(out_trade_no);
                System.out.println(ok);
                if(ok != null) {
                    if (ok.equals("ok")) {
                        map.put("success", "4");
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
                        return map;
                    }


                    map.put("msg",err_code);
                    map.put("success","3");
                    return map;//支付下单失败，重定向至订单列表

                }


                if(ParameterUtil.SUCCESS.equals(return_code)&&ParameterUtil.SUCCESS.equals(result_code)){
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
                    model.addAttribute("appId",appId);
                    model.addAttribute("timeStamp",timeStamp);
                    model.addAttribute("nonceStr",nonce_str);
                    model.addAttribute("prepayId",prepayId);
                    model.addAttribute("paySign",sign);//获取签名
                    model.addAttribute("outTradeNo",out_trade_no);//订单编号
                    model.addAttribute("money",payMoney);

                    map.put("openid",unifiedOrder.getOpenid());
                    map.put("appId",appId);
                    map.put("timeStamp",String.valueOf(timeStamp));
                    map.put("nonceStr",nonce_str);
                    map.put("prepayId",prepayId);
                    map.put("paySign",sign);//获取签名
                    map.put("outTradeNo",out_trade_no);//订单编号
                    map.put("money",payMoney);
                    map.put("success","0");

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


    /**
     * 回调
     */
    @RequestMapping({"/weixinNotify"})
    @ResponseBody
    public String weixinNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result;//返回给微信的处理结果
        String inputLine;
        String notityXml = "";
        request.setCharacterEncoding("UTF-8");
        //InputStream inStream;
        //inStream = request.getInputStream();
        //微信给返回的东西
        try {

            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
            result = WeixinUtil.setXML("fail","xml获取失败");



        }
        if (StringUtils.isEmpty(notityXml)) {
            result = WeixinUtil.setXML("fail","xml为空");



        }




        Map<String,String> resultMap = WXPayUtil.xmlToMap(notityXml);
            // 解析各种数据
            String appid =resultMap.get("appid");//应用ID
            String attach = resultMap.get("attach");//商家数据包
            String bank_type = resultMap.get("bank_type");//付款银行
            String cash_fee = resultMap.get("cash_fee");//现金支付金额
            String fee_type = resultMap.get("fee_type");//货币种类
            String is_subscribe = resultMap.get("is_subscribe");//是否关注公众账号
            String mch_id = resultMap.get("mch_id");//商户号
            String nonce_str = resultMap.get("nonce_str");//随机字符串
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
            System.out.println("**************************************************************************************************");
//            System.out.println(appid+"-------------------应用ID");
//            System.out.println(attach+"-------------------商家数据包");
            System.out.println(bank_type+"-------------------付款银行");
//            System.out.println(cash_fee+"-------------------现金支付金额");
//            System.out.println(fee_type+"-------------------货币种类");
//            System.out.println(is_subscribe+"-------------------是否关注公众账号");
//            System.out.println(mch_id+"-------------------商户号");
//            System.out.println(nonce_str+"-------------------随机字符串");
//            System.out.println(openid+"-------------------用户标识");
//            System.out.println(out_trade_no+"-------------------获取商户订单号");
//            System.out.println(result_code+"-------------------业务结果");
//            System.out.println(return_code+"------------------- SUCCESS/FAIL");
//            System.out.println(sign+"-------------------获取签名-微信回调的签名");
//            System.out.println(time_end+"-------------------支付完成时间");
//            System.out.println(total_fee+"-------------------获取订单金额");
//            System.out.println(trade_type+"-------------------交易类型");
//            System.out.println(transaction_id+"-------------------微信支付订单号");
            System.out.println("**************************************************************************************************");




        if (result_code.equals("SUCCESS")) {
            result = WeixinUtil.setXML("SUCCESS", "OK");
            //存数据到数据库
            User user = new User();
            user.setId(IdGen.uuid());
            user.setAppid(appid);
            user.setAttach(attach);
            user.setBankType(bank_type);
            user.setCashFee(AmountUtils.changeF2Y(cash_fee)); //分转元
            user.setFeeType(fee_type);
            user.setIsSubscribe(is_subscribe);
            user.setMchId(mch_id);
            user.setNonceStr(nonce_str);
            user.setOpenid(openid);
            user.setOutTradeNo(out_trade_no);
            user.setResultCode(result_code);
            user.setReturnCode(return_code);
            user.setSign(sign);
            user.setTimeEnd(time_end);
            user.setTotalFee(AmountUtils.changeF2Y(total_fee));
            user.setTradeType(trade_type);
            user.setTransactionId(transaction_id);
            user.setIsActivityId("0");
            user.setIsRefundQuery("0");
            //payChatService.save(user);
            //保存到redis
            //jedisClientSingle.set(out_trade_no,user.getBankType());
            //保存抽奖次数  一天5次抽奖
//            if(){
//
//            }
            jedisClientSingle.set(openid,bank_type);
            jedisClientSingle.set("prize_"+openid+"_sum","1");
        }else{
            result = WeixinUtil.setXML("fail", "微信返回的交易状态不正确（result_code=" + result_code + "）");
            System.err.println("微信返回的交易状态不正确（result_code=" +result_code+ "）");
        }

        result = WeixinUtil.setXML("SUCCESS", "OK");
        //return result;

//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        out.write(result.getBytes());
//        out.flush();
//        out.close();

//        String msg = "success";
//        response.setContentType("text/xml");
//        response.getWriter().println(msg);
//        response.getWriter().flush();
        return result;
    }




    /**
     * 退款接口 red
     * @return
     */
    @RequestMapping({"/refund2"})
    public String refund2(HttpServletRequest request, HttpServletResponse response,String outTradeNo,String money,String st) throws Exception{
        log.info("退款接口申请！！");

        Map<String,String> result = null;
        //退款判断
//        String bank = jedisClientSingle.get(outTradeNo);
//        System.out.println("支付方式："+bank);
//        if(bank.equals(PayUtil.JSNX_CREDIT)){
//            result.put("returncode", "ok");
//            result.put("returninfo", "退款成功");
//            System.out.println("是贷记卡！！");
//            return result;
//        }
        //微信退款信息
        String appId = ParameterUtil.APP_ID;
        String mch_id = ParameterUtil.BUSINESS_NUMBER;
        String nonce_str = WXPayUtil.MD5(WXPayUtil.generateNonceStr());
        String out_trade_no = outTradeNo;
        String out_refund_no = WeixinUtil.generateOrderSN();
        String total_fee = money;
        String refund_fee = money;
        result = wxRefund2(appId,mch_id,nonce_str,out_trade_no,out_refund_no,total_fee,refund_fee);

        String code = result.get("returncode");
        String url = "http://shwx.huhuschool.com"+ParameterUtil.URL_PAYMENT+"/html/blank.html";
        if(code.equals("ok")){
            //返回第一个页面
            if(st.equals("a")){
                return "redirect:" + url;
            }
            if(st.equals("b")){
                url = "http://shwx.huhuschool.com"+ParameterUtil.URL_PAYMENT+"/html/num.html";
                return "redirect:" + url;
            }
            if(st.equals("c")){
                url = "http://shwx.huhuschool.com"+ParameterUtil.URL_PAYMENT+"/html/sum.html";
                return "redirect:" + url;
            }
        }
        return "redirect:" + url;
    }






    /**
     * 退款接口 red
     * @return
     */
    @RequestMapping({"/refund1"})
    public String refund1(HttpServletRequest request, HttpServletResponse response,String outTradeNo,String money) throws Exception{
        log.info("退款接口申请！！");
        //退款判断

        //微信退款信息
        String appId = ParameterUtil.APP_ID;
        String mch_id = ParameterUtil.BUSINESS_NUMBER;
        String nonce_str = WXPayUtil.MD5(WXPayUtil.generateNonceStr());
        String out_trade_no = outTradeNo;
        String out_refund_no = WeixinUtil.generateOrderSN();
        String total_fee = money;
        String refund_fee = money;


        Map<String,String> result = wxRefund2(appId,mch_id,nonce_str,out_trade_no,out_refund_no,total_fee,refund_fee);
        //System.out.println("result ..."+result);
        return "success";
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
        packageParams.put("total_fee",AmountUtils.changeY2F(total_fee));
        packageParams.put("refund_fee", AmountUtils.changeY2F(refund_fee));
        //

        //获取签名
        String sign = WeixinUtil.generateSignature(packageParams,ParameterUtil.API_PASSWORD);
        packageParams.put("sign",sign);

        //map转xml
        String xml = WXPayUtil.mapToXml(packageParams);


        try {
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
            //log.info(jsonStr);

            Map<String,String> map = WXPayUtil.xmlToMap(jsonStr);
            if("success".equalsIgnoreCase(map.get("return_code"))){
                log.info("退款成功");
                result.put("returncode", "ok");
                result.put("returninfo", "退款成功");
            }else{
                log.info("退款失败");
                result.put("returncode", "error");
                result.put("returninfo", "退款失败");
            }
        } catch (Exception e) {
            log.info("退款失败");
            result.put("returncode", "error");
            result.put("returninfo", "退款失败");
        }
        return result;
    }






}
