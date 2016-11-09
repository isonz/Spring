package cn.ptp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/")
public class IndexController
{
    @RequestMapping("/")
    public String index(Model model)
    {
        String username = "";
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            username = user.getUsername();
        }catch(Exception e){}

        model.addAttribute("username", username);

        return "index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required = false) String error, @RequestParam(value="logout", required = false) String logout, Model model)
    {
        if(null != error) model.addAttribute("error", 1);
        if(null != logout) model.addAttribute("logout", 1);

        return "login";
    }

}
