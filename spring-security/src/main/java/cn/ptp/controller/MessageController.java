package cn.ptp.controller;

import cn.ptp.entity.Message;
import cn.ptp.service.MessageService;
import com.alibaba.fastjson.JSONArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController
{
    private final MessageService service;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("items", service.findAll());	//addAttribute不允许空值
        return "message/index";
    }

    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public String paged(@RequestParam(value="pageNum", defaultValue="1") int pageNum, @RequestParam(value="pageSize", defaultValue="20") int count, Model model)
    {

        int start = (pageNum - 1) * count;
        Page<Message> page = service.paged(new PageRequest(start, count));
        long total = page.getTotalElements();
        int allpage = page.getTotalPages();
        Iterator<Message> items = page.iterator();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("allpage", allpage);

        return "message/paged";
    }

    @RequestMapping(value = "/json", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String json(){
        Iterable<Message> items = service.findAll();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> info = new HashMap<>();
        String json = "";
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        for (Message item: items) {
            info.put("id", item.getId());
            info.put("name", item.getName());
            info.put("msg", item.getMsg());
            info.put("create_at", item.getCreate_at());
            jsonArray.add(JSON.toJSON(info));
            i++;
        }
        result.put("data",jsonArray);
        result.put("count", i);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="msg", defaultValue="") String msg,
            Message message
    ){
        message.setName(name);
        message.setMsg(msg);
        service.save(message);
        return "redirect:/message/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Message message)
    {
        boolean status = service.delete(id);
        return "redirect:/message/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model, Message message)
    {
        model.addAttribute("message", service.findOne(id));
        return "message/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam(value="id", required=true) long id,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="msg", defaultValue="") String msg,
            Message message
    )
    {
        service.save(message);
        return "redirect:/message/";
    }

    @ResponseBody
    @RequestMapping(value = "/sql", method = RequestMethod.GET)
    public String sql(@RequestParam(value="name", required=true) String name, Message message)
    {
        message = service.sql(message);
        return message.getMsg();
    }

}
