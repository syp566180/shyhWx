//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsruiyin.wechat.service.ShWeChatService;
import com.jsruiyin.wechat.service.WeChatService;
import com.jsruiyin.wechat.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private static Logger log = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private ShWeChatService shWeChatService;

    @Autowired
    private WeChatService weChatService;

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println(request);
        String respXml = this.shWeChatService.processRequest(request);
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.close();
    }

    /**
     * 活动登陆
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(
            value = {"/wxLogin"},
            method = {RequestMethod.GET}
    )
    public String wxLogin(HttpServletRequest request)
            throws ServletException,IOException,ParseException {
        return "redirect:" + weChatService.wxLogin(request);
    }

    /**
     *
     * @return
     */
    @RequestMapping(
            value = {"/getOpenId"}
    )
    public ModelAndView getOpenId(String redirectUrl,HttpServletRequest request) throws Exception{
        return new ModelAndView(weChatService.getOpenId(redirectUrl,request));
    }















    @RequestMapping({"/index"})
    public String index() {
        System.out.print("测试通过！！");
        return "index";
    }




}
