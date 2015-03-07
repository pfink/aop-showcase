package de.pfink.aop.showcase.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author Patrick
 */
@Aspect
@Log
public class MethodTracingAspect {

    @Pointcut("execution(* *(..))")
    public void call() {}

    @Around("de.pfink.aop.showcase.aspects.MethodTracingAspect.call()")
    public Object myTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        log.log(Level.INFO, "Triggered ''{0}'' method!", joinPoint.getSignature().getName());        
        Object retVal = joinPoint.proceed();            
        log.log(Level.INFO, "Finished ''{0}'' method successfully!", joinPoint.getSignature().getName());        
        return retVal;
    }

}
