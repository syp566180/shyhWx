//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.resp.dao;


import com.xpjz.wechat.message.resp.entiys.Article;
import com.xpjz.wechat.utils.ParameterUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {
    public ArticleDao() {
    }

    public Article TextRespMessAge() {

        Article article = new Article();
//        article.setTitle(ParameterUtil.TITLE);
//        article.setDescription(ParameterUtil.DESCRIPTION);
//        article.setPicUrl(ParameterUtil.PIC_URL);
//        article.setUrl(ParameterUtil.URL);
        return article;
    }

    public Article EventRespMessAge(String key){
        Article article = new Article();
        String description = "";     //图文消息描述
        Map<String, List<String>> map = ParameterUtil.getNewMessage();
        System.out.println("菜单按钮信息："+map.toString());
        List<String> list = map.get(key);
        article.setTitle(list.get(0));
        article.setDescription(description);
        article.setPicUrl(list.get(1));
        article.setUrl(list.get(2));
        return article;
    }


    public List<Article> EventRespMessAgeList() {
        List<Article> list = new ArrayList();
        return list;
    }



    public List<Article> EventRespMessAgeList(String Content) {
        List<Article> list = new ArrayList();
        return list;
    }


}
