//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.resp;


import org.springframework.stereotype.Component;

@Component
public class TextMessage extends BaseMessage {
    private String Content;

    public TextMessage() {
    }

    public String getContent() {
        return this.Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }
}
