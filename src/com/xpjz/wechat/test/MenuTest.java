//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.test;

import com.xpjz.wechat.function.menu.dao.MenuDao;
import com.xpjz.wechat.utils.PropertyUtil;
import com.xpjz.wechat.utils.WeixinUtil;
import redis.clients.jedis.Jedis;

public class MenuTest {
    public MenuTest() {
    }
    //private static String host = "132.232.26.102";
    //private static String password = "123456";
    private static String host = "132.232.26.102";
    private static String password = "xpjz001";
    private static int port = 6379;

    private static String ACC_TOKEN = "accessToken";

    public static void main(String[] args) {

        Jedis jedis = new Jedis(host,port);
        String accessToken = null;

        try {
            jedis.auth(password);
            accessToken = jedis.get(ACC_TOKEN);
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
