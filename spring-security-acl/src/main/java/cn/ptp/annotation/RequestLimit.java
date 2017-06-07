package cn.ptp.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)          //最高优先级
public @interface RequestLimit
{
    /**
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}

/**
 * @RequestLimit(count=1, time = 2000)
 * public String index(HttpServletRequest request, Model model){}
 */