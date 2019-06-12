package com.xpjz.wechat.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyuping on 2018/8/1.
 */
public abstract class BaseController {
    public final static String SUCCESS = "success";

    public final static String SUCCESS_1 = "success_1";

    public final static String MSG = "msg";

    public final static String DATA = "data";

    public final static String LOGOUT_FLAG = "logoutFlag";

    public final static String ERROR = "error";

    public final static String LOGID = "logid";

    public final static String DETAIL = "detail";

    public final static String NAME = "name";


    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    protected Map<String,Object> entityToJson(Object object) {
        //设置页面数据
        Map<String,Object> context = new HashMap<>();
        context.put(SUCCESS, true);
        context.put(DATA, object);
        return context;
    }


    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    protected Map<String,Object> entityToJson(Integer flag,Integer flog) {
        //设置页面数据
        Map<String,Object> context = new HashMap<>();
        context.put(SUCCESS, flag);
        context.put(SUCCESS_1, flog);
        return context;
    }




    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    protected Map<String,Object> entityToJson(Object object,String msg,String name) {
        //设置页面数据
        Map<String,Object> context = new HashMap<>();
        context.put(SUCCESS, true);
        context.put(DATA, object);
        context.put(MSG, msg);
        context.put(NAME, name);
        return context;
    }


}
