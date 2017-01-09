package cn.ptp.controller;

import cn.ptp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController
{
    private final ClientService clientService;

    @RequestMapping(value = "/adds", method = RequestMethod.GET)
    public Integer adds() {
        System.out.println("feign client add 20, 30");
        Integer result = clientService.add(20, 30);
        return result;
    }

}
