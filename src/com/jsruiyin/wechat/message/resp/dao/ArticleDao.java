//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.message.resp.dao;

import com.jsruiyin.wechat.controller.WeChatController;

import com.jsruiyin.wechat.message.resp.entiys.Article;
import com.jsruiyin.wechat.utils.ParameterUtil;

import javax.servlet.http.HttpServletRequest;
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
        article.setUrl(ParameterUtil.ACTIVITY_URL_1);

//        article1.setTitle(ParameterUtil.ACTIVITY_0);
//        article1.setDescription(description);
//        article1.setPicUrl(ParameterUtil.ACTIVTIY_PIC_URL_0);
//        article1.setUrl(ParameterUtil.ACTIVITY_URL_0);

        list.add(article);
//        list.add(article1);
        return list;
    }



    public List<Article> EventRespMessAgeList(String ttx) {
        List<Article> list = new ArrayList();
        String description = "";

        Article article = new Article();
        Article article1 = new Article();
        article.setTitle(ParameterUtil.TTX_TEXT);
        article.setDescription(description);
        article.setPicUrl(ParameterUtil.TTX_PIC_URL);
        article.setUrl(ParameterUtil.GET_TTX_URL);

        article1.setTitle(ParameterUtil.BANK_USER_TEXT);
        article1.setDescription(description);
        article1.setPicUrl(ParameterUtil.BANK_PIC_URL);
        article1.setUrl(ParameterUtil.GET_BANK_USER_URL);

        list.add(article);
        list.add(article1);
        return list;
    }


}
