package com.xpjz.wechat.dao;

import com.xpjz.wechat.entity.user.User;

import java.beans.Transient;

/**
 * Created by chenyuping on 2018/8/6.
 */
//@MyBatisDao
public interface PayDao {


    void save(User user);

}
