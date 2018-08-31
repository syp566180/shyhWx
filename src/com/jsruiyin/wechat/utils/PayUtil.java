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
    public static final String CFT = "CFT";   //零钱包
    //贷记卡
    public static final String JSNX_CREDIT = "JSNX_CREDIT";  //泗洪农商行贷记卡
    //社保卡
    //public static final String

    //方法
    public static String getHttp(String url){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        String responseContent = null; // 响应内容
        CloseableHttpResponse response= null;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();// 响应体
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 正确返回
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
                if (client != null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("结果："+responseContent);
        return responseContent;
    }
}
