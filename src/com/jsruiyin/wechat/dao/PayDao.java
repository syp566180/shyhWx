package com.jsruiyin.wechat.dao;

import com.jsruiyin.common.persistence.annotation.MyBatisDao;
import com.jsruiyin.wechat.entity.user.User;

/**
 * Created by chenyuping on 2018/8/6.
 */
//@MyBatisDao
public interface PayDao {

    void save(User user);

}
