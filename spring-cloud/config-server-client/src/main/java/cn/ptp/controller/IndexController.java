package cn.ptp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class IndexController extends BaseController
{
    @Value("${from}")
    private String from;

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

    @RequestMapping("/fresh")
    public String refresh(){
        return "<form action='/refresh' method='post'><input type='submit' /></form>";
    }

}
