package com.xpjz.wechat.aop;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Component //配置文件中启动自动扫描功能
@Aspect //声明该类是切面类
public class SystemAop {

    @Pointcut("execution(* com.xpjz.wechat.test.aop.AopDao.test(..))")
    public void project(){
        System.out.println("111");
    }


    @Before("project()")
    public void before(){
        System.out.println("前置通知");
    }

    @AfterReturning("project()")
    public void after(){
        System.out.println("后置通知");
    }

}
