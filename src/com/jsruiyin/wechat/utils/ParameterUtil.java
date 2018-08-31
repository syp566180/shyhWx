//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.utils;

import java.net.URLEncoder;

public class ParameterUtil {
        //参数
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
        public static final String ORDERPAID = "ORDERPAID";
        public static final String SYSTEMERROR = "SYSTEMERROR";
        public static final String TTX = "TTX";
        //域名指向
        public static final String URL_SESSION = "http://shwx.huhuschool.com";
        //微信项目名称 显网
        //public static final String URL_WEB = "/weChatXw";
        //微信项目名称 测试
        public static final String URL_WEB = "/weChat";
        //泗洪页面项目 名称
        //public static final String URL_BANK = "/shwx";
        //泗洪页面项目 测试
        public static final String URL_BANK = "/shwxCs";
        //泗洪活动项目地址 线网
        //public static final String URL_PAYMENT = "/payment";
        //
        //泗洪活动项目地址 测试
        public static final String URL_PAYMENT = "/payment_cs";
        //活动主路径
        public static final String URL_PAYMENT_INDEX = "/html/index.html";
        //图片地址
        public static final String URL_IMG = "/img/news/imgs";
        //pay
        public static final String API_PASSWORD = "zGwVjPqaXakbAxQh4umrrfUfqIEK5DXv";
        public static final String BUSINESS_NUMBER = "1466639502";
        //线网
//        public static final String API_PASSWORD = "zGwVjPqaXakbAxQh4umrrfUfqIEK5DXa";
//        public static final String BUSINESS_NUMBER = "1314457401";

        //测试
        public static final String TO_KEN = "376028_d";
        public static final String APP_ID = "wx1a4e3c5b7c5c1619";
        public static final String APPSECRET = "dc3b74380cd13ce5f005419b69d7c529";


//        //显网
//        public static final String TO_KEN = "376028_b";
//        public static final String APP_ID = "wxa7396c52420a4806";
//        public static final String APPSECRET = "a94d7d313f732e73cb44d825910c5216";

        //功能路径 页面地址
        public static final String INDEX = "/index.html";

        public static final String GN_URL = "/views/pages";
        //电子银行业务
        public static final String DZYHYW = "/electronic/electronic_ bank.html";
        //贷款业务
        public static final String DKYW = "/loan/loan_business.html";
        //存款业务
        public static final String CKYW = "/deposit/deposit_service.html";
        //理财业务
        public static final String LCYW = "/financial services/financing.html";




        public static final String TITLE = "欢迎访问微信平台";
        public static final String DESCRIPTION = "请选择菜单或输入您想要了解的内容";
        public static final String PIC_URL = ImgUtil.getZy();
        public static final String URL = URL_SESSION+URL_BANK+INDEX;
        public static final String LOGIN_URL = URL_SESSION+URL_WEB+"/shBank/wxLogin";
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


        public static final String ACTIVITY_0 = "我的活动";
        public static final String ACTIVTIY_PIC_URL_0 = URL_SESSION+"/shyhWx/image/draw-bg.jpg";
        public static final String ACTIVITY_URL_0 = URL_SESSION+"/wx-marketing-activities/caishendao/index.html";



        public static final String ACTIVITY_1 = "七夕抢红包";
        public static final String ACTIVTIY_PIC_URL_1 = URL_SESSION+URL_BANK+ParameterUtil.URL_IMG+"/draw-bg.jpg";
        public static final String ACTIVITY_URL_1 =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                        + "&redirect_uri="+URLEncoder.encode(LOGIN_URL)
                        + "&response_type=code"
                        + "&scope="+SNSAPI_USERINFO
                        + "&state="+WeixinUtil.generateOrderSN()+"#wechat_redirect";


        //public static final String ACTIVITY_URL_1 = "";









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
        public static final String TTX_TEXT = "微信天天学";
        public static final String TTX_PIC_URL = ImgUtil.getTtx();
        public static final String TTX_URL = "http://shwx.huhuschool.com/wx/answer/html/home.html?openId=";
        public static final String GET_TTX_URL =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                        + "&redirect_uri="+URLEncoder.encode(GET_URL+TTX_URL)
                        + "&response_type=code"
                        + "&scope="+SNSAPI_BASE
                        + "&state=STATE#wechat_redirect";



        //***********行内员工绑卡*****//
        public static final String BANK_USER_TEXT = "行内员工绑定";
        public static final String BANK_PIC_URL = "http://shwx.huhuschool.com/wx/answer/html/info.html?openId=";
        public static final String BANK_URL = "http://shwx.huhuschool.com/wx/answer/html/info.html?openId=";
        public static final String GET_BANK_USER_URL =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID
                        + "&redirect_uri="+URLEncoder.encode(GET_URL+BANK_URL)
                        + "&response_type=code"
                        + "&scope="+SNSAPI_BASE
                        + "&state=STATE#wechat_redirect";


                    public ParameterUtil() {
                    }
}
