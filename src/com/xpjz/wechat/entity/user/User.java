package com.xpjz.wechat.entity.user;

import com.xpjz.wechat.entity.Base;

/**
 * Created by chenyuping on 2018/8/6.
 */
public class User extends Base{

        private String attach;          //商家数据包
        private String bankType;        //付款银行
        private String cashFee;         //现金支付金额 （单位：分）
        private String feeType;         //货币种类
        private String isSubscribe;     //是否关注公众账号
        private String mchId;           //商户号
        private String nonceStr;        //随机字符串
        private String openid;          //用户标识
        private String outTradeNo;      //获取商户订单号
        private String resultCode;      //业务结果
        private String returnCode;      //SUCCESS/FAIL
        private String sign;            //获取签名-微信回调的签名
        private String timeEnd;         //支付完成时间
        private String totalFee;        //获取订单金额 （单位：分）
        private String tradeType;       //交易类型
        private String transactionId;   //微信支付订单号
        private String isActivityId;    //是否是活动订单
        private String isRefundQuery;   //是否退款


    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getIsActivityId() {
        return isActivityId;
    }

    public void setIsActivityId(String isActivityId) {
        this.isActivityId = isActivityId;
    }

    public String getIsRefundQuery() {
        return isRefundQuery;
    }

    public void setIsRefundQuery(String isRefundQuery) {
        this.isRefundQuery = isRefundQuery;
    }
}
