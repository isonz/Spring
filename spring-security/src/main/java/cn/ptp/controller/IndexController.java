package cn.ptp.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/")
public class IndexController
{
    @RequestMapping("/")
    public String index(Model model)
    {
        String username = "";
        List<String> authorities = new ArrayList<String>();
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = user.getUsername();
            for(GrantedAuthority authority : user.getAuthorities()) {
                authorities.add(authority.getAuthority());
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
    @ResponseBody
    public String test()
    {
        return "This is Test Page...";
    }

}
