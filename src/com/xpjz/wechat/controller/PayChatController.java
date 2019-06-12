package com.xpjz.wechat.controller;


import com.xpjz.wechat.service.PayChatService;
import com.xpjz.wechat.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;





import javax.servlet.http.HttpServletRequest;


import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by chenyuping on 2018/8/1.
 */
@Controller
@RequestMapping({"/pay"})
public class PayChatController extends BaseController{
//
//    private static Logger log = LoggerFactory.getLogger(PayChatController.class);
//
//    @Autowired
//    private PayChatService payChatService;
//
//    /**
//     * 退款返回
//     */
//    @RequestMapping({"/fhindex"})
//    public String fhindex(){
//        return "redirect:" + ParameterUtil.ACTIVITY_URL_1;
//    }
//
//
//    /**
//     * 支付成功跳转方法
//     */
//    @RequestMapping({"/index"})
//    public String index(String outTradeNo,
//                        String money,
//                        String openId,
//                        String name) throws Exception{
//        String url = payChatService.index(outTradeNo,money,openId,name);
//        return "redirect:"+url;
//    }
//
//
//    /**
//     * 跳转方法 跳转支付 重定向  pay.html
//     */
//    @RequestMapping({"/refund2"})
//    public String payHtml(String outTradeNo,String money,String st,String openId) throws Exception{
//        String url = payChatService.reimburse(outTradeNo,money,st,openId);
//        return "redirect:" + url;
//    }
//
//
//
//
//    /**
//     * 中转方法 转发路径获取参数 map<String,String></>
//     */
//    @RequestMapping({"/requestData"})
//    @ResponseBody
//    public Map<String,String> requestData(String Url,
//                              String data
//                              ) throws Exception{
//        return payChatService.requestData(Url,data);
//    }
//
//
//    /**
//     * 跳转方法 跳转支付 重定向  pay.html
//     */
//    @RequestMapping({"/payhtml"})
//    public String payHtml(String openId,String state,String nickname) throws Exception{
//        String url = payChatService.payHtml(openId,state,nickname);
//        return "redirect:" + url;
//    }
//
//    /**
//     * 公众号支付订单接口
//     * @param payMoney
//     * @param openId
//     * @param state
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping({"/jsApi"})
//    @ResponseBody
//    public Map<String,String> jsApi(String payMoney,
//                      String openId,
//                      String state,
//                      HttpServletRequest request
//                                    ) throws Exception {
//            return payChatService.jsApi(payMoney,openId,state,request);
//        }
//
//
//    /**
//     * 回调
//     */
//    @RequestMapping({"/weixinNotify"})
//    @ResponseBody
//    public String weixinNotify(HttpServletRequest request) throws Exception{
//        return payChatService.weixinNotify(request);
//    }
//
//
//    /**
//     * 退款接口 red
//     * @return
//     */
//    @RequestMapping({"/reimburse"})
//    public String reimburse(
//                          String outTradeNo,
//                          String money,
//                          String st,
//                          String openId) throws Exception{
//        log.info("参数："+openId);
//        return "redirect:" + payChatService.reimburse(outTradeNo,money,st,openId);
//    }

}
