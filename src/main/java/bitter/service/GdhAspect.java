package bitter.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GdhAspect {

//    引入新的功能接口
    @DeclareParents(value = "bitter.service.BillyService+", defaultImpl = EncoreableImpl.class)
    public static Encoreable ec1;

    @Pointcut("execution(* bitter.service.BillyService.getLocation(..))")
    public void billyGetLocation() {}

    @Around("billyGetLocation() && this(encoreable)")
    public String sayGdh(ProceedingJoinPoint joinPoint, Encoreable encoreable) {
        try{
            System.out.println("before, gaoduanhei!");
            String a = (String) joinPoint.proceed();
            System.out.println("finish, gaoduanhei!");
            System.out.println("encore: ");
            encoreable.sayEncore(a);
            return "Chengdu";
        } catch (Throwable e) {
            System.out.println("error! erua, gaoduanhei!");
        }
        return null;
    }

}
