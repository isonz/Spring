package cn.ptp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController
{
    private final Logger logger = Logger.getLogger(getClass());
    private final RestTemplate restTemplate;;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public String add() {
        System.out.println("test");
        return restTemplate.getForEntity("http://client_test/add?a=10&b=20", String.class).getBody();
    }

}
