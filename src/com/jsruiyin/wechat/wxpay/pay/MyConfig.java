package com.jsruiyin.wechat.wxpay.pay;

import com.jsruiyin.wechat.utils.ParameterUtil;
import com.jsruiyin.wechat.wxpay.sdk.IWXPayDomain;
import com.jsruiyin.wechat.wxpay.sdk.WXPayConfig;
import java.io.*;
/**
 * Created by chenyuping on 2018/7/31.
 */
public class MyConfig extends WXPayConfig {
    private byte[] certData;

    public MyConfig() throws Exception {
        String certPath = CERT_PATH;
        System.out.println("路径..."+certPath);
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    //我要推荐url
    public String getRecommend(){
        String url = "http://weixin.shnsh.net/WxyhServer/ShareRecommend?mpId=rcb&openId=";
        //获取openId

        return url;
    }

    public String getAppID() {
        return ParameterUtil.APP_ID;
    }

    public String getMchID() {
        return ParameterUtil.BUSINESS_NUMBER;
    }

    public String getKey() {
        return ParameterUtil.API_PASSWORD;
    }


    public IWXPayDomain getWXPayDomain(){
        return null;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    /**微信支付api证书路径*/
    //public static final String CERT_PATH = "/usr/share/nginx/html/apiclient_cert.p12"；

    public static final String CERT_PATH = "/usr/share/nginx/html/apiclient_cert.p12";
    /**微信统一下单url*/
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**微信申请退款url*/
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**微信红包接口url*/
    public static final String RED_URL =  "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";

    /**微信支付通知url*/
    public static final String NOTIFY_URL = "此处url用于接收微信服务器发送的支付通知，并处理商家的业务";

    /**微信交易类型:公众号支付*/
    public static final String TRADE_TYPE_JSAPI = "JSAPI";

    /**微信交易类型:原生扫码支付*/
    public static final String TRADE_TYPE_NATIVE = "NATIVE";

    /**微信甲乙类型:APP支付*/
    public static final String TRADE_TYPE_APP = "APP";

    /**红包uid*/
    public static final String UID = "23623";

    /**红包接口 apikey */
    public static final String API_KEY = "nynkFS8keZDtGnvM54OcqhHDkgE5j96G";

    /**第三方微信红包创建接口 */
    public static final String CREATE_RED = "http://www.yaoyaola.cn/index.php/exapi/hbticket";

    /**第三方微信红包发送接口*/
    public static final String GET_RED = "http://www.yaoyaola.cn/index.php/exapi/gethb";

    /** 天天学系统路径 */
    public static final String TTX_URL = "http://weixin.shnsh.net/WxyhServer_old/ExamSystem/Init/Loader?mpId=rcb&ticks=636657169871328510&xp=8kpmv4CVF74WcjKV%2cy1VDr0F5p9wptfZZ0WJfiAozGXYUFNpkoaLxLroa6eAGeqX";

    /** 行内绑卡 */
    public static final String BANK_URL = "";


}
