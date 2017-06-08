package cn.ptp.annotation;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;



public class AclInterceptor extends HandlerInterceptorAdapter
{
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        HttpSession s=request.getSession();
        s.setAttribute("host", "127.0.0.1");
        s.setAttribute("siteName", "localhost");
        //角色权限控制访问
        return aclControl(request, response,handler);
    }

    /**角色权限控制访问*/
    private boolean aclControl(HttpServletRequest request, HttpServletResponse response, Object handler) throws AclException
    {
        HttpSession session=request.getSession();
        if(handler instanceof HandlerMethod){
            HandlerMethod hm=(HandlerMethod)handler;
            Object target=hm.getBean();
            Class<?> clazz=hm.getBeanType();
            Method m= hm.getMethod();
            try {
                if (clazz!=null && m != null ) {
                    boolean isClzAnnotation= clazz.isAnnotationPresent(Acl.class);
                    boolean isMethondAnnotation=m.isAnnotationPresent(Acl.class);
                    Acl acl = null;

                    //如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
                    if(isMethondAnnotation){
                        acl=m.getAnnotation(Acl.class);
                    }else if(isClzAnnotation){
                        acl=clazz.getAnnotation(Acl.class);
                    }
                    String chmod = acl.chmod();
                    String chown = acl.chown();

                    System.out.println(chmod);
                    System.out.println(chown);

                    Object obj = session.getAttribute("group");
                    String curUserType = obj == null ? "" : obj.toString();

                    //进行角色访问的权限控制，只有当前用户是需要的角色才予以访问。
                    boolean isEquals = curUserType.equals("user");
                    if(!isEquals){
                        throw new AclException();

                        //401未授权访问
                        //response.setStatus(401);
                        //return false;
                    }
                }
            }catch(AclException e){
                throw e;
            } catch (Exception e) {
                logger.error("发生异常: ", e);
            }
        }

        return true;
    }


}
