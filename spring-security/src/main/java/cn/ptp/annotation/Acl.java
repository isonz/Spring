package cn.ptp.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Order(Ordered.HIGHEST_PRECEDENCE)          //最高优先级
public @interface Acl
{
    String chmod() default "URD,AR,R";
    String chown() default "system:system";
}
