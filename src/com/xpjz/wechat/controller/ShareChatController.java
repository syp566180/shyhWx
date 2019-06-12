package com.xpjz.wechat.controller;

import com.xpjz.wechat.service.ShareChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by syp on 2018/9/26.
 */
@Controller
@RequestMapping({"/share"})
public class ShareChatController extends BaseController{
    private static Logger log = LoggerFactory.getLogger(ShareChatController.class);

    @Autowired
    private ShareChatService shareChatService;
    /**
     * 微信分享
     * @return
     */
//    @RequestMapping({"/partake"})
//    @ResponseBody
//    public Map<String,String> partake(String url) throws Exception{
//            return shareChatService.partake(url);
//    }



}
