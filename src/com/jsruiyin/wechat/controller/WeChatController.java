//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsruiyin.wechat.service.ShWeChatService;
import com.jsruiyin.wechat.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;

@Controller
@RequestMapping({"/shBank"})
public class WeChatController {
    @Autowired
    private ShWeChatService shWeChatService;

    public WeChatController() {
    }

    @RequestMapping(
            value = {"/weChatToken"},
            method = {RequestMethod.GET}
    )
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("请求连接。。。");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature===    " + signature);
        System.out.println("timestamp===    " + timestamp);
        System.out.println("nonce=======    " + nonce);
        System.out.println("echostr=====    " + echostr);
        PrintWriter out = response.getWriter();
        if(SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("校验成功！！！");
            out.print(echostr);
        }

        out.close();
        out = null;
        System.out.println("连接成功。。。");
        System.out.println("GET请求结束。。。");
    }

    @RequestMapping(
            value = {"/weChatToken"},
            method = {RequestMethod.POST}
    )
    public void DoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我是post");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println(request);
        String respXml = this.shWeChatService.processRequest(request);
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.close();
    }

    @RequestMapping(
            value = {"/wxLogin"},
            method = {RequestMethod.GET}
    )
    public String wxLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,ParseException {
        System.out.println("授权登路...");
        HttpSession session = request.getSession();
        String code=request.getParameter("code");
        String state= WeixinUtil.generateOrderSN();
        System.out.println("state ..."+state);
        String url = ConfigUrlUtil.ATUH2_URL+"appid="+ ParameterUtil.APP_ID+
                "&secret=" +ParameterUtil.APPSECRET+
                "&code=" +code+
                "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        if(jsonObject == null || jsonObject.getInteger("errcode") != null || jsonObject.getString("openid") == null) {
              //这里需要抛异常 如果返回值没有 或者 出现错误返回errcode 或者 没有拿到openid
        }
        String openid=jsonObject.getString("openid");       //用户openid 唯一
        //用户的access_token
        String token=jsonObject.getString("access_token");
        System.out.println("openid:   "+openid);
        System.out.println("access_token:   "+token);
        String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        if(userInfo == null || userInfo.getInteger("errcode") != null ) {

        }
        System.out.println("用户信息:"+userInfo);
        //url = "login";
        System.out.println("forward重定向地址{" + url + "}");
//        ModelAndView model = new ModelAndView(url);
//        model.addObject("userInfo",userInfo);
//        model.addObject("openid",openid);
//        model.addObject("nickname",userInfo.get("nickname"));
//        model.addObject("state",state);
        String nickname = userInfo.getString("nickname");
        nickname = URLEncoder.encode(nickname, "UTF-8");//转码
        System.out.println("nickname::::::  "+nickname);
        url = ParameterUtil.URL_SESSION+ParameterUtil.URL_PAYMENT+ParameterUtil.URL_PAYMENT_INDEX+"?openid="+openid+"&nickname="+nickname+"&state="+state;
        return "redirect:" + url;
        //return model;
    }

    /**
     *
     * @return
     */
    @RequestMapping(
            value = {"/getOpenId"}
    )
    public ModelAndView getOpenId(String redirectUrl,HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("获取openId");
        String code = request.getParameter("code");
        String name = "微信用户***";
        System.out.println("重定向页面；1"+ redirectUrl);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                +ParameterUtil.APP_ID+ "&secret=" +ParameterUtil.APPSECRET+ "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        System.out.println("jsonObject:"+jsonObject);
        if(jsonObject == null || jsonObject.getInteger("errcode") != null || jsonObject.getString("openid") == null) {
            //这里需要抛异常 如果返回值没有 或者 出现错误返回errcode 或者 没有拿到openid
        }
        String openid = jsonObject.getString("openid");       //用户openid 唯一
        String token = jsonObject.getString("access_token");
        System.out.println("openid:=="+openid);
        String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=" +token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        if(userInfo == null || userInfo.getInteger("errcode") != null ) {

        }else{
            name = userInfo.getString("nickname");
        }
        System.out.println("name:=="+name);
        name = URLEncoder.encode(name, "UTF-8");//转码
        System.out.println("重定向页面；2"+ redirectUrl);
        redirectUrl = redirectUrl+openid+"&name="+name;
        String andViewUrl = "redirect:"+redirectUrl;
        System.out.println("重定向页面 3"+andViewUrl);
        return new ModelAndView(andViewUrl);
    }















    @RequestMapping({"/index"})
    public String index() {
        System.out.print("测试通过！！");
        return "index";
    }




}
