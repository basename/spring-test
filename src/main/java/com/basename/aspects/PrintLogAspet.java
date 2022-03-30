package com.basename.aspects;

import com.basename.interfaces.Printlog;
import netscape.javascript.JSObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrintLogAspet {


    @Around("@annotation(printlog)")
    public Object PrintLog(ProceedingJoinPoint joinPoint, Printlog printlog) throws Throwable {
        Integer level = printlog.level();
        String name = printlog.name();

        long start = System.currentTimeMillis();

        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        }finally {
            long t = System.currentTimeMillis() - start;
            System.err.println("[Metrics] " + name + ": " + t + "ms");
        }
    }
}
