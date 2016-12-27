package cn.ptp;

import cn.ptp.entity.Message;
import cn.ptp.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStart.class)
public class RedisTests
{
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private final int expired_time = 3600;		//过期时间（秒）

	@Test
	public void testString() throws Exception
	{
		set("aa:ff:cc:dd", "ffff", 200, TimeUnit.SECONDS);
		Assert.assertEquals("ffff", get("aa:ff:cc:dd"));

		set("aaad", "gggg");	//使用默认过期时间
		Assert.assertEquals("gggg", get("aaad"));
	}

	@Test
	public void testObj() throws Exception
	{
		// 保存对象
		User user = new User();

		user.setUsername("张小生");
		user.setOpenid("AAAAAAAAAAA-BBB");
		setObj(user.getUsername(), user);

		user.setUsername("蝙蝠侠");
		user.setOpenid("CCCCCC-BBB");
		setObj(user.getUsername(), user);

		Message message = new Message();
		message.setName("蜘蛛侠");
		message.setMsg("蜘蛛侠蜘蛛侠蜘蛛侠蜘蛛侠蜘蛛侠");
		setObj(message.getName(), message);

		User user1 = (User) getObj("张小生");
		User user2 = (User) getObj("蝙蝠侠");
		Message message1 = (Message) getObj("蜘蛛侠");

		Assert.assertEquals("AAAAAAAAAAA-BBB", user1.getOpenid());
		Assert.assertEquals("CCCCCC-BBB", user2.getOpenid());
		Assert.assertEquals("蜘蛛侠蜘蛛侠蜘蛛侠蜘蛛侠蜘蛛侠", message1.getMsg());
	}

	private void set(String key, String value)
	{
		stringRedisTemplate.opsForValue().set(key, value, expired_time, TimeUnit.SECONDS);
	}

	private void set(String key, String value, int expired_time)
	{
		stringRedisTemplate.opsForValue().set(key, value, expired_time, TimeUnit.SECONDS);
	}

	private void set(String key, String value, int expired_time, TimeUnit timeUnit)
	{
		stringRedisTemplate.opsForValue().set(key, value, expired_time, TimeUnit.SECONDS);
	}

	private String get(String key)
	{
		return stringRedisTemplate.opsForValue().get(key);
	}

	//---------- obj
	private void setObj(String key, Object obj)
	{
		redisTemplate.opsForValue().set(key, obj, expired_time, TimeUnit.SECONDS);
	}

	private Object getObj(String key)
	{
		return redisTemplate.opsForValue().get(key);
	}
}
