package com.jsruiyin.wechat.service;

import com.jsruiyin.wechat.dao.PayDao;
import com.jsruiyin.wechat.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by chenyuping on 2018/8/1.
 */
//@Service("payChatService")
public class PayChatService implements PayService {


    @Autowired
    private PayDao payDao;




    public void save(User user) {
        payDao.save(user);
    }


}
