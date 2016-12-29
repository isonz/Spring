package cn.ptp;

import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageRepositoryTests
{
    @Autowired
    private MessageRepository messageRepository;

    private Message message = new Message();

    @Test
    @Rollback
    public void testCurd() throws Exception
    {
        // insert一条数据，并select出来验证
        message.setName("AAA");
        message.setMsg("BBBBBBB");
        messageRepository.save(message);
        Message m = messageRepository.findName("AAA");
        Assert.assertEquals("BBBBBBB", m.getMsg());

        // update一条数据，并select出来验证
        m.setMsg("CCCCCCCCCC");
        messageRepository.save(m);
        m = messageRepository.findName("AAA");
        Assert.assertEquals("CCCCCCCCCC", m.getMsg());

        // 删除这条数据，并select验证
        messageRepository.delete(m.getId());
        m = messageRepository.findName("AAA");
        Assert.assertEquals(null, m);

        // 分页查询
        Pageable pageable = new PageRequest(0, 20);
        Page<Message> page = this.messageRepository.findAll(pageable);
        List<Message> items = page.getContent();
        for(Message item : items){
            Assert.assertNotEquals(null, item.getId());
            Assert.assertNotEquals(null, item.getName());
        }
        Assert.assertEquals(6, page.getTotalElements());
    }

}
