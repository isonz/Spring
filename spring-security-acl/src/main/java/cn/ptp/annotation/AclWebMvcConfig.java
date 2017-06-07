package cn.ptp.annotation;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AclWebMvcConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new AclInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new AclInterceptor());
    }
}
