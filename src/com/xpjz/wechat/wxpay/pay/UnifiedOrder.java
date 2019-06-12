package com.xpjz.wechat.wxpay.pay;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 统一下单 shenyuping 2018-07-30
 */

@XmlRootElement(name = "xml")
public class UnifiedOrder {
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 公众账号密钥
     */
    private String appsecret;
    /**
     * 商户号
     */
    private String mch_id;


    /**
     * 商户key
     */
    private String mch_key;

    /**
     * 附加数据(说明)
     */
    private String attach;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 随机串
     */
    private String nonce_str;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 终端IP（用户）
     */
    private String spbill_create_ip;
    /**
     * 总金额
     */
    private String total_fee;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * WEB
     */
    private String device_info;

    /**
     * 统一下单接口
     */
    private String prepay_id;

    /**
     * 时间戳
     * @return
     */
    private String timeStamp;

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getMch_key() {
        return mch_key;
    }

    public void setMch_key(String mch_key) {
        this.mch_key = mch_key;
    }

}
