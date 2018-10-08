//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.utils;

import java.net.URLEncoder;

public class ParameterUtil {
        //参数
        public static final String SUCCESS = PropertyUtil.get("SUCCESS");
        public static final String FAIL = PropertyUtil.get("FAIL");
        public static final String ORDERPAID = PropertyUtil.get("ORDERPAID");
        public static final String SYSTEMERROR = PropertyUtil.get("SYSTEMERROR");
        public static final String TTX = PropertyUtil.get("TTX");
        public static final String CS = PropertyUtil.get("CS");

        //域名指向
        public static final String URL_SESSION = PropertyUtil.get("URL_SESSION");
        //活动指向
        public static final String URL_WX = PropertyUtil.get("URL_WX");
        //项目完整路径 wx
        public static final String URL_SESSION_WX = URL_SESSION+URL_WX;
        //微信项目名称 显网
        public static final String URL_WEB = PropertyUtil.get("URL_WEB");
        //微信工具项目路径
        public static final String URL_SESSION_WEB = URL_SESSION+URL_WEB;
        //泗洪页面项目 名称
        public static final String URL_BANK = PropertyUtil.get("URL_BANK");
        //泗洪活动项目地址 线网
        public static final String URL_PAYMENT = PropertyUtil.get("URL_PAYMENT");
        //活动主路径
        public static final String URL_PAYMENT_INDEX = PropertyUtil.get("URL_PAYMENT_INDEX");
        //图片地址
        public static final String URL_IMG = PropertyUtil.get("URL_IMG");
        //pay
        public static final String API_PASSWORD = PropertyUtil.get("API_PASSWORD");
        public static final String BUSINESS_NUMBER = PropertyUtil.get("BUSINESS_NUMBER");

        //支付地址
        public static final String URL_PAY = PropertyUtil.get("URL_PAY");
        public static final String URL_PAY_INDEX = PropertyUtil.get("URL_PAY_INDEX");
        public static final String URL_PAY_BACK = PropertyUtil.get("URL_PAY_BACK");


        //退款接口
        public static final String REFUND_URL = PropertyUtil.get("REFUND_URL");

        public static final String TO_KEN = PropertyUtil.get("TO_KEN");
        public static final String APP_ID = PropertyUtil.get("APP_ID");
        public static final String APPSECRET = PropertyUtil.get("APPSECRET");


        //功能路径 页面地址
        public static final String INDEX = PropertyUtil.get("INDEX");

        public static final String GN_URL = PropertyUtil.get("GN_URL");
        //电子银行业务
        public static final String DZYHYW = PropertyUtil.get("DZYHYW");
        //贷款业务
        public static final String DKYW = PropertyUtil.get("DKYW");
        //存款业务
        public static final String CKYW = PropertyUtil.get("CKYW");
        //理财业务
        public static final String LCYW = PropertyUtil.get("LCYW");




        public static final String TITLE = PropertyUtil.get("TITLE");
        public static final String DESCRIPTION = PropertyUtil.get("DESCRIPTION_1");
        public static final String PIC_URL = ImgUtil.getZy();
        public static final String URL = URL_SESSION+URL_BANK+INDEX;
        public static final String LOGIN_URL = URL_SESSION+URL_WEB+"/shBank/wxLogin";
        //获取openId 跳转地址
        public static final String GET_URL = URL_SESSION+URL_WEB+"/shBank/getOpenId?redirectUrl=";
        public static final String CLICK = "click";
        public static final String VIEW = "view";
        public static final String SNSAPI_BASE = "snsapi_base"; //（不弹出授权页面，直接跳转，只能获取用户openid）
        public static final String SNSAPI_USERINFO = "snsapi_userinfo"; //（弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
        //public static final int PRIZE_SUM = 2;
        public static final String NOTIFY_URL = URL_SESSION+URL_WEB+"/pay/weixinNotify"; //回调地址



        public static final String ACCOUNT_INFORMATION = "账号信息";
        public static final String LOAN_APPLICATION = "贷款申请";
        public static final String LOAN_KEY = "1";
        public static final String LOAN_PIC_URL = ImgUtil.getDksq();
        public static final String LOAN_URL = "https://www.js96008.com:8443/wxbank/static/index.html?Page=CreditApply";

        public static final String LOAN_REDIRECT_URL = LOAN_URL;
//        "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
//                    + "&redirect_uri="+URLEncoder.encode(LOAN_URL)
//                    + "&response_type=code"
//                    + "&scope="+SNSAPI_USERINFO
//                    + "&state=STATE#wechat_redirect";

        public static final String PRODUCT_CENTER = "产品中心";


        public static final String SMALL_BUSINESS_PURSE = "创业小钱包";
        //public static final String SMALL_KEY = "2";
        public static final String SMALL_URL = URL_SESSION+"/wallet/index.html";
        public static final String LOAN_BUSINESS = "贷款业务";
        public static final String LOAN_B_KEY = "3";
        public static final String LOAN_B_URL = ConfigUrlUtil.DKYW_URL;
        public static final String LOAN_B_PIC_URL = ImgUtil.getDkyw();
        public static final String ELECTRONIC_BANKING_BUSINESS = "电子银行业务";
        public static final String ELECTRONIC_KEY = "4";
        public static final String ELECTRONIC_URL = ConfigUrlUtil.DZYHYW_URL;
        public static final String ELECTRONIC_PIC_URL = ImgUtil.getDzyhyw();
        public static final String DEPOSIT_SERVICE = "存款业务";
        public static final String DEPOSIT_KEY = "5";
        public static final String DEPOSIT_URL = ConfigUrlUtil.CKYW_URL;
        public static final String DEPOSIT_PIC_URL = ImgUtil.getCkyw();
        public static final String FINANCIAL_SERVICES = "理财业务";
        public static final String FINANCIAL_KEY = "6";
        public static final String FINANCIAL_URL = ConfigUrlUtil.LCYW_URL;
        public static final String FINANCIAL_PIC_URL = ImgUtil.getLcyw();



        public static final String MY_BANK = "我的银行";


        public static final String ACTIVITY = "我的活动";
        public static final String ACTIVITY_KEY = "9";


//        public static final String ACTIVITY_0 = "我的活动";
//        public static final String ACTIVTIY_PIC_URL_0 = URL_SESSION+"/shyhWx/image/draw-bg.jpg";
//        public static final String ACTIVITY_URL_0 = URL_SESSION+"/wx-marketing-activities/caishendao/index.html";



        public static final String ACTIVITY_1 = PropertyUtil.get("ACTIVITY_1");
        public static final String ACTIVTIY_PIC_URL_1 = URL_SESSION +
                                    URL_BANK +
                                    ParameterUtil.URL_IMG +
                                    PropertyUtil.get("DRAW_LOGO");

        public static final String ACTIVITY_ZQHQ =  ParameterUtil.URL_SESSION +
                                                    ParameterUtil.URL_WX +
                                                    ParameterUtil.URL_PAYMENT +
                                                    ParameterUtil.URL_PAYMENT_INDEX +
                                                    PropertyUtil.get("QUESTION_MARK") +
                                                    PropertyUtil.get("ACTIVITY_PARAMETER");

        public static final String ACTIVITY_URL_1 =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                        + "&redirect_uri="+URLEncoder.encode(LOGIN_URL)
                        + "&response_type=code"
                        + "&scope="+SNSAPI_USERINFO
                        + "&state="+WeixinUtil.generateOrderSN()+"#wechat_redirect";


    public static final String GETACTIVITYURL =
                 ParameterUtil.URL_SESSION +
                ParameterUtil.URL_WX +
                ParameterUtil.URL_PAYMENT +
                PropertyUtil.get("URL_PAYMENT_INDEX_FU") +
                "?openId=OPENID"+
                "&nickname=NICKNAME"+
                "&state=STATE"+
                "&code=001";





    //***********国庆绑卡*****//
    public static final String ACTIVITY_USER_TEXT = "我的绑卡";
    public static final String ACTIVITY_PIC_URL = URL_SESSION +
                                                    URL_BANK +
                                                    ParameterUtil.URL_IMG +
                                                    PropertyUtil.get("CARD_LOGO");
    //绑卡路径
    //"http://shwx.huhuschool.com/wx/answer/html/info.html?openId=";
    public static final String ACTIVITY_URL =
                                    PropertyUtil.get("URL_SESSION") +
                                    PropertyUtil.get("URL_WX") +
                                    PropertyUtil.get("URL_PAYMENT") +
                                    PropertyUtil.get("CREATE_CARD") +
                                    PropertyUtil.get("QUESTION_MARK") +
                                    PropertyUtil.get("PARAMETER_OPENID") + "=";

    public static final String ACTIVITY_GET_BANK_USER_URL =
            "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                    + "&redirect_uri="+URLEncoder.encode(GET_URL+ACTIVITY_URL)
                    + "&response_type=code"
                    + "&scope="+SNSAPI_BASE
                    + "&state=STATE#wechat_redirect";









        public static final String RECOMMEND_IT = "我要推荐";
        public static final String RECOMMEND_KEY = "7";
        public static final String RECOMMEND_URL = "http://weixin.shnsh.net/WxyhServer/ShareRecommend?mpId=rcb&openId=";


        public static final String GET_OPENID_URL =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                        + "&redirect_uri="+URLEncoder.encode(GET_URL+RECOMMEND_URL)
                        + "&response_type=code"
                        + "&scope="+SNSAPI_BASE
                        + "&state=STATE#wechat_redirect";


        public static final String RECOMMEND_PIC_URL = ImgUtil.getWytj();
        public static final String POINT_OF_VIEW = "网点一览";
        //public static final String POINT_KEY = "8";
        public static final String POINT_URL = "http://weixin.shnsh.net/WxyhServer/InstInfo/Loader/List?mpId=rcb";
        //public static final String POINT_PIC_URL = URL_SESSION+"/weChat/image/sh.png";




        //public static final String SOCIAL_SECURITY_KEY = "10";
        //public static final String SOCIAL_SECURITY_PIC_URL = ImgUtil.getSbk();
        public static final String SOCIAL_SECURITY_URL = ConfigUrlUtil.SBK_URL;
        public static final String SOCIAL_SECURITY = "社保查询";




        public static final String PRIZZE_KEY = "11";
        public static final String PRIZZE = "中奖名单";
        //public static final String PRIZZE_PIC_URL = URL_SESSION+"/weChat/image/sh.png";
        //public static final String PRIZZE_URL = "http://weixin.shnsh.net/shweb/pages/query.html";


        //***********天天学**********//
        public static final String TTX_TEXT = PropertyUtil.get("TTX_TEXT");
        public static final String TTX_PIC_URL = ImgUtil.getTtx();
        public static final String TTX_URL = PropertyUtil.get("URL_SESSION") +
                                             PropertyUtil.get("URL_WX") +
                                             PropertyUtil.get("TTX_URL") ;
        public static final String GET_TTX_URL =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                        + "&redirect_uri="+URLEncoder.encode(GET_URL+TTX_URL)
                        + "&response_type=code"
                        + "&scope="+SNSAPI_BASE
                        + "&state=STATE#wechat_redirect";



        //***********行内员工绑卡*****//
        public static final String BANK_USER_TEXT = PropertyUtil.get("BANK_USER_TEXT");
        public static final String BANK_PIC_URL = ImgUtil.getTtx();
        public static final String BANK_URL = PropertyUtil.get("URL_SESSION") +
                                                PropertyUtil.get("URL_WX") +
                                                PropertyUtil.get("BANK_URL") ;
        public static final String GET_BANK_USER_URL =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                        + "&redirect_uri="+URLEncoder.encode(GET_URL+BANK_URL)
                        + "&response_type=code"
                        + "&scope="+SNSAPI_BASE
                        + "&state=STATE#wechat_redirect";


                    public ParameterUtil() {
                    }
}
