package com.mrh.cheers.Intercepter;

import com.alibaba.fastjson.JSON;
import com.mrh.cheers.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component("logAspect")
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());



    // 配置织入点
    @Pointcut("@annotation(com.mrh.cheers.annotation.Log)")
    public void logPointCut() {
    }


    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        //获取类名 方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        try {
            // 获得注解
            Log log = getAnnotationLog(joinPoint);
            //获取请求参数
            Object[] args = joinPoint.getArgs();
            String params = JSON.toJSONString(args[0]);
            //获取注解定义操作行为
            String action = log.action();
            logger.info(action + "【"+className +"】" + "【" + methodName +"】" +"开始执行，请求参数："+ params);
        } catch (Exception exp) {
            logger.error("【"+className +"："+methodName+"】"+ "执行异常，" +"异常信息:{}", exp.toString());
        }

    }

    /**
     * 后置通知 用于拦截操作，在方法返回后执行
     * @param joinPoint 切点
     */
    @AfterReturning(returning = "ret",pointcut = "logPointCut()")
    public void doAfter(JoinPoint joinPoint,Object ret) {
        handleLog(joinPoint, null,ret);
    }

    /**
     * 拦截异常操作，有异常时执行
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e,null);
    }

    private void handleLog(JoinPoint joinPoint, Exception e,Object ret) {
        // 获得类名称
        String className = joinPoint.getTarget().getClass().getName();
        // 获得方法名称
        String methodName = joinPoint.getSignature().getName();
        try {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            //操作行为
            String action = controllerLog.action();
            //是否打印返回值
            boolean isPrint = controllerLog.isPrint();
            if(isPrint){
                logger.info(action + "【"+className +"】" + "【" + methodName +"】"+"执行成功，返回值："+ ret);
            }else {
                logger.info(action + "【"+className +"】" + "【" + methodName +"】" +"执行成功！");
            }
        } catch (Exception exp) {
            logger.error("【"+className +"："+methodName+"】"+ "执行异常，" +"异常信息:{}", exp.toString());
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private static Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }



}
