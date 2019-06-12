//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.req;

public class LinkMessage extends BaseMessage {
    private String Title;
    private String Description;
    private String Url;

    public LinkMessage() {
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getUrl() {
        return this.Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }
}
