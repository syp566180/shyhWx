package com.jsruiyin.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.jsruiyin.wechat.utils.AuthUtil;
import com.jsruiyin.wechat.utils.ConfigUrlUtil;
import com.jsruiyin.wechat.utils.ParameterUtil;
import com.jsruiyin.wechat.utils.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;

/**
 * Created by syp on 2018/9/26.
 */
@Service
public class WeChatService {

    private static Logger log = LoggerFactory.getLogger(WeChatService.class);
    /**
     *
     * @param request
     * @param
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    public String wxLogin(HttpServletRequest request)
            throws ServletException,IOException,ParseException {
        String code=request.getParameter("code");
        String state= WeixinUtil.generateOrderSN();
        String url = ConfigUrlUtil.ATUH2_URL+"appid="+ ParameterUtil.APP_ID+
                "&secret=" +ParameterUtil.APPSECRET+
                "&code=" +code+
                "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        log.info("jsonObject   {}",jsonObject);
        if(jsonObject == null || jsonObject.getInteger("errcode") != null || jsonObject.getString("openid") == null) {
            //这里需要抛异常 如果返回值没有 或者 出现错误返回errcode 或者 没有拿到openid
            //404页面
        }
        String openid=jsonObject.getString("openid");       //用户openid 唯一
        //用户的access_token
        String token=jsonObject.getString("access_token");
        String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        if(userInfo == null || userInfo.getInteger("errcode") != null ) {
            //404页面

        }
        System.out.println("用户信息:"+userInfo);
        String nickname = userInfo.getString("nickname");
        nickname = WeixinUtil.filterEmoji(nickname);
        nickname = URLEncoder.encode(nickname, "UTF-8");//转码
        String requestUrl = "https://shwx.huhuschool.com/wx/festival/html/time.html";
        url = ParameterUtil.ACTIVITY_ZQHQ.replace("OPENID",openid).
                replace("NICKNAME",nickname).
                replace("STATE",state);
         long date = System.currentTimeMillis() / 1000;
        //活动结束时间
         long endDate = 1538582400;
         if(date<endDate){
             requestUrl = url;
         }
        log.info("活动主路径：{}",requestUrl);
        return requestUrl;

    }



    public String getOpenId(String redirectUrl,HttpServletRequest request) throws Exception{
        log.info("获取openId");
        String code = request.getParameter("code");
        String name = "微信用户***";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                +ParameterUtil.APP_ID+ "&secret=" +ParameterUtil.APPSECRET+ "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        if(jsonObject == null || jsonObject.getInteger("errcode") != null || jsonObject.getString("openid") == null) {
            //这里需要抛异常 如果返回值没有 或者 出现错误返回errcode 或者 没有拿到openid
        }
        String openid = jsonObject.getString("openid");       //用户openid 唯一
        String token = jsonObject.getString("access_token");
        String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        if(userInfo == null || userInfo.getInteger("errcode") != null ) {

        }else{
            name = userInfo.getString("nickname");
        }
        name = URLEncoder.encode(name, "UTF-8");//转码
        redirectUrl = redirectUrl+openid+"&name="+name;
        String andViewUrl = "redirect:"+redirectUrl;
        log.info("路径..."+andViewUrl);
        return andViewUrl;
    }




}
