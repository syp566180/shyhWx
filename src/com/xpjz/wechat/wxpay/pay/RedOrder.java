package com.xpjz.wechat.wxpay.pay;

import com.xpjz.wechat.wxpay.sdk.WXPayUtil;

/**
 * Created by chenyuping on 2018/8/2.
 */
public class RedOrder {

    //随机字符串 nonce_str
    private String nonce_str;
    //商户订单号
    private String mch_billno;
    //商户号
    private String mch_id;
    //公众号appid
    private String wxappid;
    //商户名称
    private String send_name;
    //openid re_openid
    private String re_openid;
    //付款金额 total_amount
    private int total_amount;
    //红包发放总人数 total_num
    private int total_num;
    //红包祝福语 wishing
    private String wishing;
    //Ip地址 client_ip
    private String client_ip;
    //活动名称 act_name
    private String act_name;
    //备注 remark
    private String remark;
    //签名
    private String sign;

    //随机参数生成
    public String NonceStr() throws Exception{
        return WXPayUtil.MD5(WXPayUtil.generateNonceStr());
    }






    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getMch_billno() {
        return mch_billno;
    }

    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getRe_openid() {
        return re_openid;
    }

    public void setRe_openid(String re_openid) {
        this.re_openid = re_openid;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
