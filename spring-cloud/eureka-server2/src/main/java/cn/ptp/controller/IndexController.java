package cn.ptp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
//@RequestMapping("/")
public class IndexController
{

    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required = false) String error, @RequestParam(value="logout", required = false) String logout, Model model)
    {
        if(null != error) model.addAttribute("error", 1);
        if(null != logout) model.addAttribute("logout", 1);

        return "login";
    }


}
