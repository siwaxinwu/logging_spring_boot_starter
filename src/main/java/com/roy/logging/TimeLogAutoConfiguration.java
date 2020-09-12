package com.roy.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * description：name:表示要配置的属性，havingvalue表示期待的值，matchIfMissing表示没配置这个属性的时候默认是true
 * author：dingyawu
 * date：created in 0:03 2020/9/12
 * history:
 */
@Configuration
@Aspect
@EnableAspectJAutoProxy
@ConditionalOnProperty(prefix = "time.log", name = "enable", havingValue = "true", matchIfMissing = true)
public class TimeLogAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(TimeLogAutoConfiguration.class);

    @Around("@annotation(com.roy.logging.TimeLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取方法名：public java.lang.string com.roy.logging.XXX.hello();
        String methodName = proceedingJoinPoint.getSignature().toLongString().split(" ")[2];
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("方法：{} 耗时：{}ms",methodName, end - start);
        return result;
    }
}
