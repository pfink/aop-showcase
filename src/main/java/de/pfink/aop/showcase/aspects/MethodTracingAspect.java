package de.pfink.aop.showcase.aspects;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author Patrick
 */
@Aspect
@Log
public class MethodTracingAspect {
    @Pointcut("execution(* de.pfink.aop.showcase.*.*(..))")
    public void methodsInShowcasePackageWithoutSubPackages() {}

    @Before("de.pfink.aop.showcase.aspects.MethodTracingAspect.methodsInShowcasePackageWithoutSubPackages()")
    public void traceOnMethodBegin(JoinPoint joinPoint) {
        log.log(Level.INFO, "Triggered ''{0}'' method!", joinPoint.getSignature().getName());        
    }
    
    @After("de.pfink.aop.showcase.aspects.MethodTracingAspect.methodsInShowcasePackageWithoutSubPackages()")
    public void traceOnMethodEnd(JoinPoint joinPoint) {
        log.log(Level.INFO, "Finished ''{0}'' method successfully!", joinPoint.getSignature().getName());     
    }
}
