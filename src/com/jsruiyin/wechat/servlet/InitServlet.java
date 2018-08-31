//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.servlet;

import com.jsruiyin.wechat.thread.TokenThread;
import com.jsruiyin.wechat.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public InitServlet() {
    }

    public void init() throws ServletException {
        System.out.println("1111111111111111111111111111");
        TokenThread.appid = this.getInitParameter("appid");
        TokenThread.appsecret = this.getInitParameter("appsecret");
        log.info("weixin api appid:{}", TokenThread.appid);
        log.info("weixin api appsecret:{}", TokenThread.appsecret);
        if(!"".equals(TokenThread.appid) && !"".equals(TokenThread.appsecret)) {
            System.out.println("3333333333333333333333333333333");
            (new Thread(new TokenThread())).start();
        } else {
            log.error("appid and appsecret configuration error, please check carefully.");
            System.out.println("2222222222222222222222222222222");
        }

    }
}
