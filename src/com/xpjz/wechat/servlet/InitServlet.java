//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.servlet;

import com.xpjz.wechat.thread.TokenThread;
import com.xpjz.wechat.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public InitServlet() {
    }

    public void init() {
        log.info("启动...1");
        TokenThread.appid = this.getInitParameter("appid");
        TokenThread.appsecret = this.getInitParameter("appsecret");
        log.info("weixin api appid:{}", TokenThread.appid);
        log.info("weixin api appsecret:{}", TokenThread.appsecret);
        if(!"".equals(TokenThread.appid) && !"".equals(TokenThread.appsecret)) {
            log.info("启动...2");
            log.info("启动...3...结束");
          //  (new Thread(new TokenThread())).start();
        } else {
            log.error("启动...失败");
            log.error("appid and appsecret configuration error, please check carefully.");
        }

    }
}
