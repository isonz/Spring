package cn.ptp.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)          //最高优先级
public @interface Acl
{
    String chmod() default "URD,AR,R";
    String chown() default "system:system";
}
