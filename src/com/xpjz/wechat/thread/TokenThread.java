//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.thread;

import com.xpjz.wechat.entity.AccessToken;
import com.xpjz.wechat.utils.CommonUtil;
import com.xpjz.wechat.utils.ParameterUtil;
import com.xpjz.wechat.utils.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class TokenThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);
    public static String appid = ParameterUtil.WxConfig.APP_ID;
    public static String appsecret = ParameterUtil.WxConfig.APPSECRET;
    public static AccessToken accessToken = null;
    private static String host = "132.232.26.102";
    private static String password = "xpjz001";
    private static int port = 6379;
    private static String accessTokenRedis = "accessTokenRedis";
    public static String jsApiTicketRedis = "jsApiTicketRedis";
    public TokenThread() {
    }

    public void run() {
        while(true) {
            try {
                accessToken = CommonUtil.getToken();
                if(null != accessToken) {
                    String jsApiTicket = CommonUtil.getToken(accessToken.getToken());
                    log.info("获取jsApiTicket成功， jsApiTicket:{}", jsApiTicket);
                    System.out.println("启动线程...");
                    Jedis jedis = new Jedis(host,port);
                    try {
                        jedis.auth(password);
                        System.out.println("连接成功");
                        log.info("获取access_token成功，有效时长{}秒 token:{}", Integer.valueOf(accessToken.getExpiresIn()), accessToken.getToken());
                        jedis.set(accessTokenRedis, accessToken.getToken());
                        jedis.set(jsApiTicketRedis,jsApiTicket);
                        jedis.close();
                        Thread.sleep((long)((accessToken.getExpiresIn() - 200) * 1000));
                        System.out.println("休眠线程...");
                    } catch (Exception var4) {
                        System.out.println("连接失败！！");
                        Thread.sleep(60 * 1000);
                        var4.printStackTrace();
                    }
                } else {
                    System.out.println("启动线程失败   accessToken..."+accessToken);
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException var5) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException var3) {
                    log.error("{}", var3);
                }

                log.error("{}", var5);
            }
        }
    }
}
