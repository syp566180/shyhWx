package com.jsruiyin.wechat.utils;

import com.jsruiyin.wechat.entity.AccessToken;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenyuping on 2018/7/10.
 */
public class CommonUtil {

    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public CommonUtil() {
    }

    public static AccessToken getToken() {
        AccessToken token = null;
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+PropertyUtil.get("APP_ID")+"&secret="+PropertyUtil.get("APPSECRET");
        JSONObject jsonObject = HttpUtil.httpRequest(requestUrl, "GET",null);
        if(null != jsonObject) {
            try {
                token = new AccessToken();
                token.setToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException var4) {
                token = null;
                log.error("获取token失败 errcode11111:{} errmsg:{}", Integer.valueOf(jsonObject.getInt("errcode")), jsonObject.getString("errmsg"));
            }
        }

        return token;
    }


    /**
     * jsApiTicket
     * @return
     */
    public static String getToken(String accessToken) {
        String jsApiTicket = null;
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
        JSONObject jsonObject = HttpUtil.httpRequest(requestUrl, "GET",null);
        if(null != jsonObject) {
            try {
                jsApiTicket = jsonObject.getString("ticket");
                System.out.println("jsApiTicket:_____"+jsApiTicket);
            } catch (JSONException var4) {
                jsApiTicket = null;
                log.error("获取token失败 errcode:{} errmsg:{}", Integer.valueOf(jsonObject.getInt("errcode")), jsonObject.getString("errmsg"));
            }
        }

        return jsApiTicket;
    }

}
