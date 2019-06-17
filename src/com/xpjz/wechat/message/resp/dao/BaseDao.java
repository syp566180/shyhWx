//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.resp.dao;

import com.xpjz.wechat.message.resp.ImageMessage;
import com.xpjz.wechat.message.resp.NewsMessage;
import com.xpjz.wechat.message.resp.TextMessage;
import com.xpjz.wechat.message.resp.entiys.Article;
import com.xpjz.wechat.message.resp.entiys.Image;
import com.xpjz.wechat.utils.MessageUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseDao {

    private static Logger log = LoggerFactory.getLogger(BaseDao.class);

    @Autowired
    private NewsMessage newsMessage;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TextMessage textMessage;
    @Autowired
    private ImageMessage imageMessage;



    public BaseDao() {
    }

    /**
     *
     * @param fromUserName      发送方微信号，若为普通用户，则是一个OpenID
     * @param toUserName        接收方微信号
     * @param msgType           消息类型
     * @param key               点击菜单key
     * @param eventType         菜单类型
     * @return
     */
    public String  NewsCreate(String fromUserName,String toUserName,String msgType,String eventType,String key) {
        //param  消息类型
        //文本text
        log.info("消息类型:"+msgType);
        log.info("点击菜单key:"+key);
        if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime((new Date()).getTime());
            textMessage.setContent("您输入的是文本信息");
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            return TextMessage(textMessage);
        }else if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime((new Date()).getTime());
            return NewMessage(newsMessage,eventType,key);
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
        String repXml = MessageUtil.messageToXml(textMessage);
        log.info("文本回复内容："+repXml);
        return repXml;
    }

    private String NewMessage(@NotNull NewsMessage newsMessage,String eventType,String key){
                if(MessageUtil.EVENT_TYPE_CLICK.equals(eventType)){
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                }
                 List<Article> articleList = new ArrayList();
                 Article article = this.ArticleCreate(key);
                 articleList.add(article);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                String repXml = MessageUtil.messageToXml(newsMessage);
                log.info("点击菜单响应内容："+repXml);
            return repXml;
    }


    @Contract(pure = true)
    public String ImageMessage(String fromUserName, String toUserName){
                imageMessage.setToUserName(fromUserName);
                imageMessage.setFromUserName(toUserName);
                imageMessage.setCreateTime(new Date().getTime());
                Image image = new Image();
                image.setMediaId("HfGX3gwchAAjOopjrzJLR1L57CNM2Pb30LO5ud84zuo");
                imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
                imageMessage.setImage(image);
                String repXml = MessageUtil.messageToXml(imageMessage);
                log.info("图片消息回复："+repXml);
                return repXml;
    }









}
