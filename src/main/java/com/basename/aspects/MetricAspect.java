package com.basename.aspects;

import com.basename.interfaces.MetricTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspect {

    @Around("@annotation(metricTime)")
    public Object metric(ProceedingJoinPoint joinPoint, MetricTime metricTime) throws Throwable {
        String name = metricTime.value();

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            Long t = System.currentTimeMillis() - start;
            System.err.println("[metrics] "+ name + ":" + t + "ms");
        }
    }
}
