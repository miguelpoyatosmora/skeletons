package com.miguelpoyatosmora.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.miguelpoyatosmora.service.*.*(..))")
    public void service() {
    }

    @Pointcut("execution(* com.miguelpoyatosmora.controller.*.*(..))")
    public void controller() {
    }

    @Around("service() || controller()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } finally {
            if (logger.isDebugEnabled()) {
                logger.debug(new StringBuilder()
                        .append(joinPoint.getTarget().getClass())
                        .append(" method ").append(joinPoint.getSignature().getName())
                        .append(" params ").append(Arrays.toString(joinPoint.getArgs()))
                        .append(" returned ").append(result)
                        .toString());
            }
        }
    }
}