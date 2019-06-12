package com.xpjz.wechat.entity;

/**
 * Created by chenyuping on 2018/8/6.
 */
public abstract class Base {


    private static final long serialVersionUID = 4967651108535453951L;
    public String id;     //项目id
    public String appid;  //应用id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
