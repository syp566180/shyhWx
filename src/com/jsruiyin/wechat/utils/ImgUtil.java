package com.jsruiyin.wechat.utils;


/**
 * Created by chenyuping on 2018/8/3.
 */
public class ImgUtil {
    //
    public static String JPEG = PropertyUtil.get("JPEG");


    public static String getZy(){
        return ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("zy") +
                JPEG;
    }


    public static String getWzyl(){
        return  ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("wzyl")+
                JPEG;
    }


    public static String getDzyhyw(){
        return  ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("dzyhyw") +
                JPEG;
    }


    public static String getWytj(){
        return  ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("wytj") +
                JPEG;
    }


    public static String getCkyw(){
        return  ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("ckyw") +
                JPEG;
    }


    public static String getDksq(){
        return  ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("dksq") +
                JPEG;
    }


    public static String getLcyw(){
        return  ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("lcyw") +
                JPEG;

    }

    public static String getDkyw(){
        return ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("dkyw") +
                JPEG;
    }


    public static String getSbk(){
        return  ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("sbk") +
                JPEG;
    }


    public static String getTtx(){
        return ParameterUtil.URL_SESSION +
                ParameterUtil.URL_BANK +
                ParameterUtil.URL_IMG +
                PropertyUtil.get("ttx") +
                JPEG;
    }

}
