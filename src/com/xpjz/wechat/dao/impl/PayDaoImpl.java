package com.xpjz.wechat.dao.impl;

import com.xpjz.common.persistence.annotation.MyBatisDao;
import com.xpjz.wechat.dao.PayDao;
import com.xpjz.wechat.entity.user.User;

import java.beans.Transient;


@MyBatisDao
public class PayDaoImpl implements PayDao {

    @Override
    @Transient()
    public void save(User user) {

    }
}
