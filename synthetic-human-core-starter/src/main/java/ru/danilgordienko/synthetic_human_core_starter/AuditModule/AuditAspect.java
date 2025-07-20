package ru.danilgordienko.synthetic_human_core_starter.AuditModule;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Slf4j
//@Component
public class AuditAspect {

    @Around("@annotation(ru.danilgordienko.synthetic_human_core_starter.AuditModule.WeylandWatchingYou)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Object[] methodArgs = pjp.getArgs();
        Object result = pjp.proceed();

        String auditMessage = "\n method: " + methodName +
                "\n args: " + Arrays.toString(methodArgs) +
                "\n result: " + result;

        log.info(auditMessage);
        return result;
    }

}
