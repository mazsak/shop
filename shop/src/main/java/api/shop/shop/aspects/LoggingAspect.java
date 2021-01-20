package api.shop.shop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@EnableAspectJAutoProxy
@Configuration
public class LoggingAspect {

    private Logger logger;

    @Pointcut("execution(* api.shop.shop.service..*(..))")
    public void getServicePointcut() {
    }

    @Before("getServicePointcut()")
    public void loggingBeforeExecution(JoinPoint joinPoint) {
        logger = Logger.getLogger(joinPoint.getTarget().getClass().getName());
        logger.info(() -> {
            StringBuilder sb = new StringBuilder();
            sb
                    .append(joinPoint.getTarget().getClass().getName())
                    .append(".")
                    .append(joinPoint.getSignature().getName())
                    .append(", args:");
            for (Object o : joinPoint.getArgs()) {
                sb.append("'").append(o).append("' ");
            }
            return "Public method called: " + sb.toString();
        });
    }


    @AfterThrowing(pointcut = "getServicePointcut()", throwing = "ex")
    public void afterExceptionThrowing(JoinPoint joinPoint, Exception ex) {
        logger = Logger.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        logger.severe(() -> {
            StringBuilder sb = new StringBuilder();
            sb.append(joinPoint.getSignature()).append(", args:");
            for (Object o : joinPoint.getArgs()) {
                sb.append("'").append(o).append("'");
            }
            return "Exception thrown: " + sb.toString() + " - Exception: " + ex.getClass().getName();
        });
    }

}
