package de.pfink.aop.showcase.aspects;

import de.pfink.aop.showcase.MainApp;
import javax.persistence.EntityManager;
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
public class TransactionHandlingAspect {

    @Pointcut("execution(@javax.transaction.Transactional * *(..))")
    public void methodsWithTransactionalAnnotation() {}

    @Around("de.pfink.aop.showcase.aspects.TransactionHandlingAspect.methodsWithTransactionalAnnotation()")
    public Object wrapTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        EntityManager em = MainApp.getEntityManager();
        
        em.getTransaction().begin();        
        try {
            Object retVal = joinPoint.proceed();
            em.getTransaction().commit();
            return retVal;
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
