package cn.ptp;

import cn.ptp.controller.MessageRestController;
import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;
import cn.ptp.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageRestController.class)
public class MessageRestTests
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    @Test
    public void index() throws Exception {
        this.mvc.perform(get("/message_rest/")).andExpect(status().isOk()).andExpect((ResultMatcher) content().string(equalTo("[]")));
    }

    @Test
    public void paged() throws Exception {
        this.mvc.perform(get("/message_rest/paged").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }

    @Test
    public void serPaged() throws Exception{
        this.messageService = new MessageService(messageRepository);
        Page<Message> page =this.messageService.paged(new PageRequest(0, 20));
        given(page.getTotalElements()).willReturn(anyLong());
    }

    @Test
    public void json() throws Exception {
        this.mvc.perform(get("/message/json").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/message/add").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
        this.mvc.perform(get("/message/delete/1").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }

    @Test
    public void edit() throws Exception {
        this.mvc.perform(get("/message/edit/1").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        this.mvc.perform(get("/message/update").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }

    @Test
    public void sql() throws Exception {
        this.mvc.perform(get("/message/sql").accept(MediaType.TEXT_PLAIN)).andExpect(status().isOk());
    }

}
