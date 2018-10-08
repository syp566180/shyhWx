package com.jsruiyin.wechat.test;

import com.jsruiyin.wechat.utils.PropertyUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class PropertyTest {
    public static void main(String[] args) throws Exception{
        PropertyUtil propertyUtil = new PropertyUtil();
       // System.out.println("第一个方法    "+propertyUtil.get("EQUAL_SIGN"));


        String ACTIVITY_URL =
                PropertyUtil.get("URL_SESSION") +
                PropertyUtil.get("URL_WX") +
                PropertyUtil.get("URL_PAYMENT") +
                PropertyUtil.get("CREATE_CARD") +
                PropertyUtil.get("QUESTION_MARK") +
                PropertyUtil.get("PARAMETER_OPENID") + "=";
        System.out.println("第一个方法    "+ACTIVITY_URL);
//        System.out.println("第二个方法    "+propertyUtil.readValue(true,"URL_SESSION"));
//        Properties prop = new Properties();
//        try{
//            //读取属性文件a.properties
//            InputStream in = new BufferedInputStream (new FileInputStream("config/deploy.properties"));
//            prop.load(in);     ///加载属性列表
//            Iterator<String> it=prop.stringPropertyNames().iterator();
//            while(it.hasNext()){
//                String key=it.next();
//                System.out.println(key+":"+prop.getProperty(key));
//            }
//            in.close();
//
//            ///保存属性到b.properties文件
//            FileOutputStream oFile = new FileOutputStream("b.properties", true);//true表示追加打开
//            prop.setProperty("phone", "10086");
//            prop.store(oFile, "The New properties file");
//            oFile.close();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
    }
}