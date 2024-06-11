package comm.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
public class FirstAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    @Pointcut("within(comm.service.*Service)")
    public void isServiceLayer() {
    }

    @Pointcut("this(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {
    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelArgs() {
    }

    @Pointcut("isControllerLayer() && @args(comm.validator.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {
    }

    @Pointcut("bean(userService)")
    public void isUserServiceBean() {
    }

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    @Pointcut("execution(public Long comm.service.*Service.findById(*))")
    public void anyServiceFindByIdMethod() {
    }

    @Pointcut("execution(public * findById(*))")
    public void anyFindByIdMethod() {
    }

    //Advice

    @Before("anyServiceFindByIdMethod() " +
     "&& args(id) " +
    "&& target(service) " +
    "&& this(serviceProxy) " +
    "&& @within(transactional)")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("Before Invoke findById method in  class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyServiceFindByIdMethod()" +
    " && target(service)",
    returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info(" After returning invoked findById in class {}, with res {}", service, result);
    }

    @AfterThrowing(value = "anyServiceFindByIdMethod()" +
            " && target(service)",
            throwing = "ex")
    public void addLoggingAfterReturning(Throwable ex, Object service) {
        log.info(" After throw invoked findById in class {}, with ex {}", service, ex);
    }

    @After(value = "anyServiceFindByIdMethod() && target(service)")
    public void addLoggingAfterReturning(Object service) {
        log.info("After invoked findById in class {}", service);
    }

    //@AROUND - ОБЪЕДИНЯЕТ ВСЕ ВЫШЕПЕРЕЧИСЛЕННОЕ В ОДНО
}
