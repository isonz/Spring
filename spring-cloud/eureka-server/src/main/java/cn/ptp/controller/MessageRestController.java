package cn.ptp.controller;

import cn.ptp.entity.Message;
import cn.ptp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/message_rest")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageRestController
{
    private final MessageService service;

    //查询列表
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public Iterable<Message> index() {
        return service.findAll();
    }

    //查询分页列表
    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public Page<Message> paged(@RequestParam(value="pageNum", defaultValue="1") int pageNum, @RequestParam(value="pageSize", defaultValue="20") int count, Model model)
    {

        int start = (pageNum - 1) * count;
        Page<Message> page = service.paged(new PageRequest(start, count));
        return page;
    }

    //查询单条记录
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Message get(@PathVariable int id)
    {
        return service.findOne(id);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="msg", defaultValue="") String msg,
            Message message
    ){
        message.setName(name);
        message.setMsg(msg);
        service.save(message);
        return "SUCCESS";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable int id, Message message)
    {
        boolean status = service.delete(id);
        if(status) return "SUCCESS";
        return "FAIL";
    }

    //更新单条记录
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Message update(
            @RequestParam(value="id", required=true) long id,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="msg", defaultValue="") String msg,
            Message message
    )
    {
        return service.save(message);
    }


}
