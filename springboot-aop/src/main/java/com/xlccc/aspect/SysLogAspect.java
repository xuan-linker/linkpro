package com.xlccc.aspect;

import com.google.gson.Gson;
import com.xlccc.annotation.SysLogAnnotation;
import com.xlccc.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Linker
 * @date 2020/11/3 16:26
 * @description：
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package，需要拦截的class，以及method
     * 切点表达式： execution(...)
     * 例如：execution(public * com.example.demo.controller.*.*(..))
     */
    @Pointcut(value = "@annotation(com.xlccc.annotation.SysLogAnnotation)")
    public void logPointCut() {
    }

    /**
     * 环绕通知@Around，也可以使用@Before（前置通知）@After（后置通知）
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        try {
            saveLog(point, time);
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        SysLog sysLog = new SysLog();
        //获取请求url，ip，httpMethod
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        String httpMethod = request.getMethod();
        String url = request.getRequestURL().toString();
        sysLog.setIp(ip);
        sysLog.setHttpMethod(httpMethod);
        sysLog.setUrl(url);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        sysLog.setExecTime(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sysLog.setCreateDate(dateFormat.format(new Date()));
        SysLogAnnotation annotation = method.getAnnotation(SysLogAnnotation.class);
        if (annotation != null) {
            //注解上的描述
            sysLog.setRemark(annotation.value());
        }

        //请求的类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setClassName(className);
        sysLog.setMethodName(methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = new ArrayList<>();
            for (Object o : args
            ) {
                list.add(new Gson().toJson(o));
            }
            sysLog.setParams(list.toString());
        } catch (Exception e) {
        }
        log.info("Log: " + sysLog.toString());
    }
}
