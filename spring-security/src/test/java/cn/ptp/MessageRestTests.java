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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    RequestBuilder request = null;

    @Test
    public void index() throws Exception {
        this.mvc.perform(get("/message_rest/")).andExpect(status().isOk()).andExpect((ResultMatcher) content().string(equalTo("[]")));
    }

    @Test
    public void test_all() throws Exception
    {
        //get查一下message列表，应该为空
        request = get("/message_rest/");
        mvc.perform(request).andExpect(status().isOk()).andExpect((ResultMatcher) content().string(equalTo("[]")));

        //post提交一个message
        request = post("/message_rest/").param("id", "1").param("name", "测试大师").param("age", "20");
        this.mvc.perform(request).andExpect((ResultMatcher) content().string(equalTo("success")));

        //get获取message列表，应该有刚才插入的数据
        request = get("/message_rest/");
        mvc.perform(request).andExpect(status().isOk()).andExpect((ResultMatcher) content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

        //get一个id为1的essage
        request = get("/message_rest/1");
        mvc.perform(request).andExpect((ResultMatcher) content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":20}]")));

        //del删除id为1的message
        request = delete("/message_rest/1");
        mvc.perform(request).andExpect((ResultMatcher) content().string(equalTo("success")));

        //get查一下 message 列表，应该为空
        request = get("/message_rest/");
        mvc.perform(request).andExpect(status().isOk()).andExpect((ResultMatcher) content().string(equalTo("[]")));
    }



}
