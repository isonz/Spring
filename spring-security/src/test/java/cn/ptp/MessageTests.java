package cn.ptp;

import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTests
{
	private Message message;
    private MessageRepository messageRepository;

    private WebApplicationContext context;
    private MockMvc mockMvc;
    private RestTemplate restTemplate;

    private int port=8080;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

	@Test
	public void contextLoads()
	{
        assertEquals(1, messageRepository.count());
	}

    @Test
    public void webappBookIsbnApi() {
        Message message = restTemplate.getForObject("http://localhost:" + port +"/books/9876-5432-1111", Message.class);
        assertNotNull(message);
        assertEquals("中文测试", message.getName());
    }

}
