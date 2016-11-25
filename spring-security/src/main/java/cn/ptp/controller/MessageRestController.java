package cn.ptp.controller;

import cn.ptp.entity.Message;
import cn.ptp.service.MessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value="获取列表", notes="")
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public Iterable<Message> index() {
        return service.findAll();
    }

    //查询分页列表
    @ApiOperation(value="获取分页列表", notes="")
    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public Page<Message> paged(@RequestParam(value="pageNum", defaultValue="1") int pageNum, @RequestParam(value="pageSize", defaultValue="20") int count, Model model)
    {

        int start = (pageNum - 1) * count;
        Page<Message> page = service.paged(new PageRequest(start, count));
        return page;
    }

    //查询单条记录
    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "消息ID", required = true, dataType = "Int")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Message get(@PathVariable int id)
    {
        return service.findOne(id);
    }

    //新增记录
    @ApiOperation(value="新增记录", notes="根据标题和消息体创建")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "标题", required = true, dataType = "String"),
        @ApiImplicitParam(name = "msg", value = "内容", required = true, dataType = "String")
    })
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

    //删除单条记录
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable int id, Message message)
    {
        boolean status = service.delete(id);
        if(status) return "SUCCESS";
        return "FAIL";
    }

    //更新单条记录
    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的标题和内容信息来更新详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "消息ID", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "name", value = "详细标题", required = true, dataType = "String"),
        @ApiImplicitParam(name = "msg", value = "详细内容", required = true, dataType = "String")
    })
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
