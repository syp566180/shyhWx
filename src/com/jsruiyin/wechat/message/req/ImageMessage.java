//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.message.req;

public class ImageMessage extends BaseMessage {
    private String PicUrl;
    private String MediaId;

    public ImageMessage() {
    }

    public String getPicUrl() {
        return this.PicUrl;
    }

    public void setPicUrl(String picUrl) {
        this.PicUrl = picUrl;
    }

    public String getMediaId() {
        return this.MediaId;
    }

    public void setMediaId(String mediaId) {
        this.MediaId = mediaId;
    }
}
