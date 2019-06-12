//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.utils;


import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterUtil {

        //配置文件信息
        private static int CODE_0 = PropertyUtil.CODE_0;
        private static int CODE_1 = PropertyUtil.CODE_1;
        private static int CODE_2 = PropertyUtil.CODE_2;
        private static int CODE_3 = PropertyUtil.CODE_3;
        //菜单个数
        private static int MENU_NUM = Integer.valueOf(PropertyUtil.get("MENU_NUM",CODE_1));
        //
        public static Map<String, List<String>> getNewMessage(){
            Map<String, List<String>> map = new HashMap<>();
            List<String> list = getList();
            //title
            list.add("最新活动");
            //picUrl
            list.add("#");
            //url
            list.add("#");
            map.put(ParameterUtil.MenuConfig.mainBtn1_0_key,list);
            list = getList();
            list.add("家政服务");
            //picUrl
            list.add("#");
            //url
            list.add("#");
            map.put(ParameterUtil.MenuConfig.mainBtn2_0_key,list);
            list = getList();
            list.add("装修设计");
            //picUrl
            list.add("#");
            //url
            list.add("#");
            map.put(ParameterUtil.MenuConfig.mainBtn2_1_key,list);
            list = getList();
            list.add("JAVA设计");
            //picUrl
            list.add("#");
            //url
            list.add("#");
            map.put(ParameterUtil.MenuConfig.mainBtn2_2_key,list);
            list = getList();
            list.add("个人信息");
            //picUrl
            list.add("#");
            //url
            list.add("#");
            map.put(ParameterUtil.MenuConfig.mainBtn3_0_key,list);
            list = getList();
            list.add("订单信息");
            //picUrl
            list.add("#");
            //url
            list.add("#");
            map.put(ParameterUtil.MenuConfig.mainBtn3_1_key,list);
            return map;
        }

        @Contract(pure = true)
        public static List<String> getList(){
            List<String> list = new ArrayList<>();
            return list;
        }



        public ParameterUtil() {

        }
        //微信系统参数
        public static class  WxConfig{
            //token
            public static String TO_KEN = PropertyUtil.get("TO_KEN",CODE_0);
            //appId
            public static String APP_ID = PropertyUtil.get("APP_ID",CODE_0);
            //APPSECRET
            public static String APPSECRET = PropertyUtil.get("APPSECRET",CODE_0);
    }
        //菜单参数
        public static class MenuConfig{
            //一级菜单
            public static String mainBtn1 = PropertyUtil.get("mainBtn1",CODE_1);
            public static String mainBtn2 = PropertyUtil.get("mainBtn2",CODE_1);
            public static String mainBtn3 = PropertyUtil.get("mainBtn3",CODE_1);
            //二级菜单
            public static String mainBtn1_0_value = PropertyUtil.get("mainBtn1_01_0_value",CODE_1);
            public static String mainBtn1_0_key = PropertyUtil.get("mainBtn1_01_0_key",CODE_1);
            public static String mainBtn2_0_value = PropertyUtil.get("mainBtn1_02_0_value",CODE_1);
            public static String mainBtn2_0_key = PropertyUtil.get("mainBtn1_02_0_key",CODE_1);
            public static String mainBtn2_1_value = PropertyUtil.get("mainBtn1_02_1_value",CODE_1);
            public static String mainBtn2_1_key = PropertyUtil.get("mainBtn1_02_1_key",CODE_1);
            public static String mainBtn2_2_value = PropertyUtil.get("mainBtn1_02_2_value",CODE_1);
            public static String mainBtn2_2_key = PropertyUtil.get("mainBtn1_02_2_key",CODE_1);
            public static String mainBtn3_0_value = PropertyUtil.get("mainBtn1_03_0_value",CODE_1);
            public static String mainBtn3_0_key = PropertyUtil.get("mainBtn1_03_0_key",CODE_1);
            public static String mainBtn3_1_value = PropertyUtil.get("mainBtn1_03_1_value",CODE_1);
            public static String mainBtn3_1_key = PropertyUtil.get("mainBtn1_03_1_key",CODE_1);
        }

        //回复参数
        public static class ReqConfig{

        }

}
