//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.resp.dao;


import com.xpjz.wechat.message.resp.entiys.Article;
import com.xpjz.wechat.utils.ParameterUtil;


import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    public ArticleDao() {
    }

    public Article TextRespMessAge() {

        Article article = new Article();
        article.setTitle(ParameterUtil.TITLE);
        article.setDescription(ParameterUtil.DESCRIPTION);
        article.setPicUrl(ParameterUtil.PIC_URL);
        article.setUrl(ParameterUtil.URL);
        return article;
    }

    public Article EventRespMessAge(String param){
        Article article = new Article();
        String title = "";           //图文消息标题
        String description = "";     //图文消息描述
        String picUrl = "";          //
        String url = "";
//        Menu menu= MenuDao.getMenu();
//        int x = menu.getButton().length;
//        for (int i=0; i<x; i++){
//            Button cop = menu.getButton()[i];
//
//        }

        if(param.equals(ParameterUtil.LOAN_KEY)) {
            title = ParameterUtil.LOAN_APPLICATION;
            picUrl = ParameterUtil.LOAN_PIC_URL;
            url = ParameterUtil.LOAN_REDIRECT_URL;
        } else if(param.equals(ParameterUtil.LOAN_B_KEY)) {
            title = ParameterUtil.LOAN_BUSINESS;
            picUrl = ParameterUtil.LOAN_B_PIC_URL;
            url = ParameterUtil.LOAN_B_URL;
        } else if(param.equals(ParameterUtil.ELECTRONIC_KEY)) {
            title = ParameterUtil.ELECTRONIC_BANKING_BUSINESS;
            picUrl = ParameterUtil.ELECTRONIC_PIC_URL;
            url = ParameterUtil.ELECTRONIC_URL;
        } else if(param.equals(ParameterUtil.DEPOSIT_KEY)) {
            title = ParameterUtil.DEPOSIT_SERVICE;
            picUrl = ParameterUtil.DEPOSIT_PIC_URL;
            url = ParameterUtil.DEPOSIT_URL;
        } else if(param.equals(ParameterUtil.FINANCIAL_KEY)) {
            title = ParameterUtil.FINANCIAL_SERVICES;
            picUrl = ParameterUtil.FINANCIAL_PIC_URL;
            url = ParameterUtil.FINANCIAL_URL;
        } else if(param.equals(ParameterUtil.RECOMMEND_KEY)) {
            title = ParameterUtil.RECOMMEND_IT;
            picUrl = ParameterUtil.RECOMMEND_PIC_URL;
            url = ParameterUtil.GET_OPENID_URL;
        }
//        } else if(param.equals(ParameterUtil.PRIZZE_KEY)) {
//            title = ParameterUtil.PRIZZE;
//            picUrl = ParameterUtil.PRIZZE_PIC_URL;
//            url = ParameterUtil.PRIZZE_URL;
//        }

        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        System.out.println(title);
        System.out.println(description);
        System.out.println(picUrl);
        System.out.println(url);
        return article;
    }


    public List<Article> EventRespMessAgeList() {
        List<Article> list = new ArrayList();
        String description = "";

        Article article = new Article();
        Article article1 = new Article();
        article.setTitle(ParameterUtil.ACTIVITY_1);
        article.setDescription(description);
        article.setPicUrl(ParameterUtil.ACTIVTIY_PIC_URL_1);
        //当前时间
       // long date = System.currentTimeMillis() / 1000;
        //活动结束时间
       // long endDate = 1538582400;
       // if(date>endDate){
       //     article.setUrl("https://shwx.huhuschool.com/wx/festival/html/time.html");
       // }else {
            article.setUrl(ParameterUtil.ACTIVITY_URL_1);
       // }
        //article.setUrl("http://www.syp666.cn");
        article1.setTitle(ParameterUtil.ACTIVITY_USER_TEXT);
        article1.setDescription(description);
        article1.setPicUrl(ParameterUtil.ACTIVITY_PIC_URL);
        article1.setUrl(ParameterUtil.ACTIVITY_GET_BANK_USER_URL);
        //article1.setUrl("http://www.syp666.cn");
        list.add(article);
        list.add(article1);
        return list;
    }



    public List<Article> EventRespMessAgeList(String Content) {
        List<Article> list = new ArrayList();
        String description = "";
        int i = 0;
        Article article = new Article();
        Article article1 = new Article();
        String title = "";
        String Description = "";
        String PicUrl = "";
        String Url = "";
        String title1 = "";
        String Description1 = "";
        String PicUrl1 = "";
        String Url1 = "";
        if(Content.equals(ParameterUtil.TTX)){
            i = 1;
            title = ParameterUtil.TTX_TEXT;
            Description = description;
            PicUrl = ParameterUtil.TTX_PIC_URL;
            Url = ParameterUtil.GET_TTX_URL;
            title1 = ParameterUtil.BANK_USER_TEXT;
            Description1 = description;
            PicUrl1 = ParameterUtil.BANK_PIC_URL;
            Url1 = ParameterUtil.GET_BANK_USER_URL;
        }else if(Content.equals(ParameterUtil.CS)){
            i = 1;
            title = ParameterUtil.ACTIVITY_1;
            Description = description;
            PicUrl = ParameterUtil.ACTIVTIY_PIC_URL_1;
            Url = ParameterUtil.ACTIVITY_URL_1;
            title1 = ParameterUtil.ACTIVITY_USER_TEXT;
            Description1 = description;
            PicUrl1 = ParameterUtil.ACTIVITY_PIC_URL;
            Url1 = ParameterUtil.ACTIVITY_GET_BANK_USER_URL;
        }else{
            article = TextRespMessAge();
        }

        if(i==1) {
            article.setTitle(title);
            article.setDescription(Description);
            article.setPicUrl(PicUrl);
            article.setUrl(Url);

            article1.setTitle(title1);
            article1.setDescription(Description1);
            article1.setPicUrl(PicUrl1);
            article1.setUrl(Url1);

            list.add(article);
            list.add(article1);
        }else {
            list.add(article);
        }
        return list;
    }


}
