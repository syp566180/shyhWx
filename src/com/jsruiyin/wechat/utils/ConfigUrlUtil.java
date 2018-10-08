//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.utils;

public class ConfigUrlUtil {
    //微信接口路径
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx1a4e3c5b7c5c1619&secret=60529f9425364826964d4d346247fa37";
    public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    public static final String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
    public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
    public static final String ATUH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";


    //微信跳转页面路径
    public static final String DZYHYW_URL = ParameterUtil.URL_SESSION+ParameterUtil.URL_BANK+ParameterUtil.GN_URL+ParameterUtil.DZYHYW;
    public static final String DKYW_URL = ParameterUtil.URL_SESSION+ParameterUtil.URL_BANK+ParameterUtil.GN_URL+ParameterUtil.DKYW;
    public static final String CKYW_URL = ParameterUtil.URL_SESSION+ParameterUtil.URL_BANK+ParameterUtil.GN_URL+ParameterUtil.CKYW;
    public static final String LCYW_URL = ParameterUtil.URL_SESSION+ParameterUtil.URL_BANK+ParameterUtil.GN_URL+ParameterUtil.LCYW;
    //社保卡查询路径
    public static final String SBK_URL =
            ParameterUtil.URL_SESSION +
                    ParameterUtil.URL_WX +
            PropertyUtil.get("SBK_URL");



    public ConfigUrlUtil() {
    }
}


