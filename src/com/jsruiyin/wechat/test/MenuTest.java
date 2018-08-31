//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.test;

import com.jsruiyin.wechat.function.menu.dao.MenuDao;
import com.jsruiyin.wechat.utils.WeixinUtil;
import redis.clients.jedis.Jedis;

public class MenuTest {
    public MenuTest() {
    }
    //private static String host = "132.232.26.102";
    //private static String password = "123456";
    private static String host = "139.196.115.139";
    private static String password = "rycm";
    private static int port = 6379;

    //显网
    private static String accessToken_xw = "accessToken";
    //测试
    private static String accessToken_cs = "accessToken_cs";

    public static void main(String[] args) {

        Jedis jedis = new Jedis(host,port);
        String accessToken = null;

        try {
            jedis.auth(password);
            accessToken = jedis.get(accessToken_cs);
            System.out.println("accessToken:"+accessToken);
            jedis.close();
        } catch (Exception var4) {
            var4.printStackTrace();
            jedis.close();
        }

        if(null != accessToken) {
            int result = WeixinUtil.createMenu(MenuDao.getMenu(), accessToken);
            if(0 == result) {
                System.out.println("菜单创建成功！！！");
            }
        }

    }
}
