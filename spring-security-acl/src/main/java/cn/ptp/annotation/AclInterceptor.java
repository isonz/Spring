package cn.ptp.annotation;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AclInterceptor
{
    private Logger logger = Logger.getLogger(getClass());

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(acl)")
    //@Before(value = "within(@javax.persistence.Entity *) && @annotation(acl)")
    public void aclCtl(Acl acl) throws AclWebException,AclJsonException{
        try {
            String chmod = acl.chmod();
            String chown = acl.chown();
            System.out.println(chmod);
            System.out.println(chown);

            if(false) {
                if (true) {
                    throw new AclWebException();
                } else {
                    throw new AclJsonException();
                }
            }
        } catch (AclWebException | AclJsonException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常: ", e);
        }
    }

}
