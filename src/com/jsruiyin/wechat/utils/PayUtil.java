package com.jsruiyin.wechat.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by chenyuping on 2018/8/7.
 */
public class PayUtil {
    //支付方式
    public static final String CFT = PropertyUtil.get("CFT");   //零钱包
    //借记卡
    public static final String JSNX_CREDIT = PropertyUtil.get("JSNX_CREDIT");
    //贷记卡
    public static final String JSNX_DEBIT = PropertyUtil.get("JSNX_DEBIT");
    //
    public static final String JSNX = PropertyUtil.get("JSNX");  //泗洪农商行贷记卡
    //是否校验
    public static final String IS_BANK_CODE = PropertyUtil.get("IS_BANK_CODE");

    public boolean getIsBankCode(){
        boolean flog = false;
        if("0".equals(IS_BANK_CODE)){
            flog = true;
        }
        return flog;
    }

}
