//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jsruiyin.wechat.message.event;

public class QRCodeEvent extends BaseEvent {
    private String EventKey;
    private String Ticket;

    public QRCodeEvent() {
    }

    public String getEventKey() {
        return this.EventKey;
    }

    public void setEventKey(String eventKey) {
        this.EventKey = eventKey;
    }

    public String getTicket() {
        return this.Ticket;
    }

    public void setTicket(String ticket) {
        this.Ticket = ticket;
    }
}
