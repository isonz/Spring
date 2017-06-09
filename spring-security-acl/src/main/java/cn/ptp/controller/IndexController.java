package cn.ptp.controller;

import cn.ptp.annotation.Acl;
import cn.ptp.exception.JsonException;
import cn.ptp.exception.WebException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/")
@ApiIgnore      //忽略Swagger2
public class IndexController extends BaseController
{
    @Acl(chmod = "R,R,R", chown = "ison:ison")
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model)
    {
        String username = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorities = new ArrayList<String>();
        try {
            if(!(auth instanceof AnonymousAuthenticationToken)) {
                UserDetails user = (UserDetails) auth.getPrincipal();
                username = user.getUsername();
                //获取权限列表
                for (GrantedAuthority authority : user.getAuthorities()) {
                    authorities.add(authority.getAuthority());
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        model.addAttribute("username", username);
        String roles = authorities.toString();
        model.addAttribute("roles", authorities);

        return "index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required = false) String error, @RequestParam(value="logout", required = false) String logout, Model model)
    {
        if(null != error) model.addAttribute("error", 1);
        if(null != logout) model.addAttribute("logout", 1);

        return "login";
    }

    @RequestMapping("/test")
    //@ResponseBody
    public String test(Model model)
    {
        asset(model);
        return "test";
    }

    @RequestMapping("/myerror")
    public String myerror() throws WebException
    {
        throw new WebException("发生错误2");
    }


    @Acl(chmod = "R,R,R", chown = "ison:ison")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name){
        return "Hello " + name;
    }

}
