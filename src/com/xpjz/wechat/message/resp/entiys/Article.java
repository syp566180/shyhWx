//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.resp.entiys;

public class Article {
    private String Title;           //
    private String Description;
    private String PicUrl;
    private String Url;


    public Article() {
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return null == this.Description?"":this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getPicUrl() {
        return null == this.PicUrl?"":this.PicUrl;
    }

    public void setPicUrl(String picUrl) {
        this.PicUrl = picUrl;
    }

    public String getUrl() {
        return null == this.Url?"":this.Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }
}
