package com.baizhi.aspect;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao.LogDao;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Aspect
@Configuration
public class LogAspect {
    @Autowired
    private LogDao logDao;
    @Autowired
    private HttpServletRequest httpServletRequest;
   // @Around("execution(* com.baizhi.service.*.*(..))")
    @Around("@annotation(com.baizhi.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("admin","admin");
        //谁 时间  干了什么
        String admin = (String) session.getAttribute("admin");
        Date date = new Date();
        long time = date.getTime();
        //获得方法名字
       // String name = proceedingJoinPoint.getSignature().getName();
        //通过反射获得注解上的value值
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        LogAnnotation annotation = methodSignature.getMethod().getAnnotation(LogAnnotation.class);
        String name=annotation.value();
        Log log=new Log();
        log.setId(UUID.randomUUID().toString());
        log.setUsername(admin).setTime(new java.sql.Date(time)).setMethodname(name);
        try {
            Object proceed = proceedingJoinPoint.proceed();
            String status="success";
            log.setStatus(status);
            //入库
            logDao.insert(log);
            return proceed;//返回原始方法的返回值
        } catch (Throwable throwable) {
            String status="error";
            log.setStatus(status);
            //入库
            logDao.insert(log);
            throwable.printStackTrace();
            return null;
        }

    }
}
