package com.bateng.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.bateng.web.controller.UserController.*(..))")
    public Object  handle(ProceedingJoinPoint pjp){
        Object o = null;
//        System.out.println("执行切面");
        try {
              o = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        System.out.println("切面执行完成");
        return  o;
    }
}
