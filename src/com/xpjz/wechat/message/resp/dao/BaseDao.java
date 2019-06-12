//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.resp.dao;

import com.xpjz.wechat.message.resp.NewsMessage;
import com.xpjz.wechat.message.resp.TextMessage;
import com.xpjz.wechat.message.resp.entiys.Article;
import com.xpjz.wechat.utils.MessageUtil;
import com.xpjz.wechat.utils.ParameterUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseDao {
    @Autowired
    private NewsMessage newsMessage;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TextMessage textMessage;

    public BaseDao() {
    }

    /**
     *
     * @param fromUserName      发送方微信号，若为普通用户，则是一个OpenID
     * @param toUserName        接收方微信号
     * @param msgType           消息类型
     * @param key               点击菜单key
     * @param Content          文本消息内容
     * @return
     */
    public String  NewsCreate(String fromUserName,String toUserName,String msgType,String key,String Content) {
        String respXml;
        //param  消息类型
        //文本text
        if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime((new Date()).getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            return TextMessage(textMessage);
        }else if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime((new Date()).getTime());
            return NewMessage(newsMessage,key);
        }

    return null;

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


    public Article ArticleCreate(String key) {
        new Article();
        Article article;
        article = this.articleDao.EventRespMessAge(key);
        return article;
    }




    private String TextMessage(TextMessage textMessage){
        return MessageUtil.messageToXml(textMessage);
    }

    private String NewMessage(@NotNull NewsMessage newsMessage, String key){
                 List<Article> articleList = new ArrayList();
                 Article article = this.ArticleCreate(key);
                 articleList.add(article);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }










}
