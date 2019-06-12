//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.req;

public class VoiceMessage extends BaseMessage {
    private String MediaId;
    private String Format;

    public VoiceMessage() {
    }

    public String getMediaId() {
        return this.MediaId;
    }

    public void setMediaId(String mediaId) {
        this.MediaId = mediaId;
    }

    public String getFormat() {
        return this.Format;
    }

    public void setFormat(String format) {
        this.Format = format;
    }
}
