package cn.ptp.controller;

import cn.ptp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController
{
    private final Logger logger = Logger.getLogger(getClass());

    private final RestTemplate restTemplate;
    private final DiscoveryClient client;

    @RequestMapping(value = "/add1" ,method = RequestMethod.GET)
    public String add1() {
        System.out.println("test");
        //ResponseEntity<String> response = restTemplate.getForEntity("http://CONSUMER-RIBBON/client/addd?a=10&b=20", String.class);
        ResponseEntity<String> response = restTemplate.getForEntity("http://CLIENT-TEST/client/add?a=10&b=20", String.class);
        return response.getBody();
    }

    @RequestMapping(value = "/addd" ,method = RequestMethod.GET)
    public Integer addd(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }


    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return clientService.addService();
    }

}
