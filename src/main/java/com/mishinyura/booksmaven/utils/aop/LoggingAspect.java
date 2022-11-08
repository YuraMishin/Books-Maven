package com.mishinyura.booksmaven.utils.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.mishinyura.booksmaven.rest.impl..*(..)))")
    public Object profileControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        log.info("Executed {}.{}()", className, methodName);
        StopWatch countdown = new StopWatch();
        countdown.start();

        Object result = proceedingJoinPoint.proceed();

        countdown.stop();
        log.info("Execution time of {}.{}():: {}ms", className, methodName, countdown.getTotalTimeMillis());
        return result;
    }
}
