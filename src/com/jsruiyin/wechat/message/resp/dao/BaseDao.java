//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.message.resp.dao;

import com.jsruiyin.wechat.message.resp.NewsMessage;
import com.jsruiyin.wechat.message.resp.entiys.Article;
import com.jsruiyin.wechat.utils.MessageUtil;
import com.jsruiyin.wechat.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseDao {
    @Autowired
    private NewsMessage newsMessage;
    @Autowired
    private ArticleDao articleDao;

    public BaseDao() {
    }

    public String  NewsCreate(String fromUserName,String toUserName,String param,String Content) {
        String respXml = null;
        this.newsMessage.setToUserName(fromUserName);
        this.newsMessage.setFromUserName(toUserName);
        this.newsMessage.setCreateTime((new Date()).getTime());
        this.newsMessage.setMsgType("news");
        List<Article> articleList = new ArrayList();
        if(param.equals(ParameterUtil.ACTIVITY_KEY)) {
            articleList = this.ArticleListCreate("");

        }else if(!Content.equals("")){
            articleList = this.ArticleListCreate(Content);
        }else{
            Article article = this.ArticleCreate(param);
            articleList.add(article);
        }
        this.newsMessage.setArticleCount(articleList.size());
        this.newsMessage.setArticles(articleList);
        respXml = MessageUtil.messageToXml(this.newsMessage);
        System.out.println(respXml);
        return respXml;
    }

    public List<Article> ArticleListCreate(String Content){

        List<Article> list;
        if(!Content.equals("")) {
            list = articleDao.EventRespMessAgeList(Content);
            return list;
        }
        list = articleDao.EventRespMessAgeList();
        return list;
    }


    public Article ArticleCreate(String param) {
        new Article();
        Article article;
        if(param.equals("text")) {
            article = this.articleDao.TextRespMessAge();
        } else {
            article = this.articleDao.EventRespMessAge(param);
        }

        return article;
    }
}
