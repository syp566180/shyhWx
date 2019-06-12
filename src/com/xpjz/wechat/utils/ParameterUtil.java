//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.utils;



public class ParameterUtil {

        //配置文件信息
        private static int code_0 = PropertyUtil.CODE_0;
        private static int code_1 = PropertyUtil.CODE_1;
        private static int code_2 = PropertyUtil.CODE_2;
        private static int code_3 = PropertyUtil.CODE_3;

        public ParameterUtil() {

        }
        //微信系统参数
        public static class  WxConfig{
            //token
            public static String TO_KEN = PropertyUtil.get("TO_KEN",code_0);
            //appId
            public static String APP_ID = PropertyUtil.get("APP_ID",code_0);
            //APPSECRET
            public static String APPSECRET = PropertyUtil.get("APPSECRET",code_0);
            //redis
            public static String REDIS_HOST = PropertyUtil.get("REDIS_HOST",code_0);
            public static int REDIS_PORT = Integer.valueOf(PropertyUtil.get("REDIS_PORT",code_0));
            public static String REDIS_PWD = PropertyUtil.get("REDIS_PWD",code_0);
            public static String ACC_TOKEN = PropertyUtil.get("ACC_TOKEN",code_0);
            public static String JS_APITICKET = PropertyUtil.get("JS_APITICKET",code_0);
    }
        //菜单参数
        public static class MenuConfig{
            //一级菜单
            public static String mainBtn1 = PropertyUtil.get("mainBtn1",code_1);
            public static String mainBtn2 = PropertyUtil.get("mainBtn2",code_1);
            public static String mainBtn3 = PropertyUtil.get("mainBtn3",code_1);
            //二级菜单
            public static String mainBtn1_0_value = PropertyUtil.get("mainBtn1_01_0_value",code_1);
            public static String mainBtn1_0_key = PropertyUtil.get("mainBtn1_01_0_key",code_1);
            public static String mainBtn2_0_value = PropertyUtil.get("mainBtn1_02_0_value",code_1);
            public static String mainBtn2_0_key = PropertyUtil.get("mainBtn1_02_0_key",code_1);
            public static String mainBtn2_1_value = PropertyUtil.get("mainBtn1_02_1_value",code_1);
            public static String mainBtn2_1_key = PropertyUtil.get("mainBtn1_02_1_key",code_1);
            public static String mainBtn2_2_value = PropertyUtil.get("mainBtn1_02_2_value",code_1);
            public static String mainBtn2_2_key = PropertyUtil.get("mainBtn1_02_2_key",code_1);
            public static String mainBtn3_0_value = PropertyUtil.get("mainBtn1_03_0_value",code_1);
            public static String mainBtn3_0_key = PropertyUtil.get("mainBtn1_03_0_key",code_1);
            public static String mainBtn3_1_value = PropertyUtil.get("mainBtn1_03_1_value",code_1);
            public static String mainBtn3_1_key = PropertyUtil.get("mainBtn1_03_1_key",code_1);
        }

        //回复参数
        public static class ReqConfig{

        }

}
