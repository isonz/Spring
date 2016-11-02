package cn.ptp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/")
public class IndexController
{
    private ModelAndView mv = new ModelAndView();

    @RequestMapping("/")
    public ModelAndView index() {
        mv.setViewName("index");
		mv.addObject("name", "ison.zzzz");
        return mv;
    }
}
