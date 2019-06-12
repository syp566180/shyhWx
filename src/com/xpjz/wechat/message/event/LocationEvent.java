//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xpjz.wechat.message.event;

public class LocationEvent extends BaseEvent {
    private String Latitude;
    private String Longitude;
    private String Precision;

    public LocationEvent() {
    }

    public String getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(String latitude) {
        this.Latitude = latitude;
    }

    public String getLongitude() {
        return this.Longitude;
    }

    public void setLongitude(String longitude) {
        this.Longitude = longitude;
    }

    public String getPrecision() {
        return this.Precision;
    }

    public void setPrecision(String precision) {
        this.Precision = precision;
    }
}
