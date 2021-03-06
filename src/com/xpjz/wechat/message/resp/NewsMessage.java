//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.resp;

import com.xpjz.wechat.message.resp.entiys.Article;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NewsMessage extends BaseMessage {
    private int ArticleCount;
    private List<Article> Articles;

    public NewsMessage() {
    }

    public int getArticleCount() {
        return this.ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        this.ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return this.Articles;
    }

    public void setArticles(List<Article> articles) {
        this.Articles = articles;
    }
}
