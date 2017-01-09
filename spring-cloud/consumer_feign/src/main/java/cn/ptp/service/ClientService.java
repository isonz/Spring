package cn.ptp.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "CLIENT-TEST", fallback = ClientServiceHystrix.class)
public interface ClientService
{
    //value = "/client/add" 为CLIENT-TEST的客户端资源路径
    @RequestMapping(value = "/client/add", method = RequestMethod.GET)
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}

/*
@FeignClient(value = "CLIENT-TEST")
public interface ClientService
{
    @RequestMapping(value = "/client/add", method = RequestMethod.GET)
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
*/
