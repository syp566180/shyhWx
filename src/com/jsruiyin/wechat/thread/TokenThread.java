//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.thread;

import com.jsruiyin.wechat.entity.AccessToken;
import com.jsruiyin.wechat.utils.CommonUtil;
import com.jsruiyin.wechat.utils.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class TokenThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);
    public static String appid = "";
    public static String appsecret = "";
    public static AccessToken accessToken = null;
    private static String host = PropertyUtil.get("host");
    private static String password = PropertyUtil.get("redisPassword");
    private static int port = 6379;
    //显网
    //private static String accessToken_xw = "accessToken";
    //
    //public static String jsApiTicket_xw = "jsApiTicket_xw";
    //测试
    private static String accessTokenRedis = PropertyUtil.get("accessTokenRedis");
    //测试
    public static String jsApiTicketRedis = PropertyUtil.get("jsApiTicketRedis");
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
