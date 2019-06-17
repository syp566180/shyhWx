package com.xpjz.wechat.test;

import com.xpjz.wechat.test.aop.AopDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/spring/spring-context.xml"})
public class AopTest {

            @Autowired
            private AopDao aopDao;

            @Test
            public void testUpm(){
                aopDao.test();
            }


}
