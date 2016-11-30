package cn.ptp.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@Configuration
public class RedisClusterConfig
{
    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private int redirects;

    @Value("${spring.redis.cluster.timeout}")
    private Long timeout;

    @Autowired
    RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(new RedisClusterConfiguration(clusterNodes));
    }

    @Autowired
    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        // just used StringRedisTemplate for simplicity here.
        return new StringRedisTemplate(factory);
    }

    /*
    @Bean
    public RedisClusterConfiguration getClusterConfiguration() {
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("spring.redis.cluster.nodes", clusterNodes);
        source.put("spring.redis.cluster.timeout", timeout);
        source.put("spring.redis.cluster.max-redirects", redirects);
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }

    @Bean
    public JedisClusterConnection getJedisClusterConnection() {
        return (JedisClusterConnection) jedisConnectionFactory().getConnection();
    }

    @Bean
    public RedisTemplate getRedisTemplate() {
        RedisTemplate clusterTemplate = new RedisTemplate();
        clusterTemplate.setConnectionFactory(jedisConnectionFactory());
        clusterTemplate.setKeySerializer(new StringRedisSerializer());
        clusterTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return clusterTemplate;
    }

    /*
    @Bean
    private RedisConnectionFactory connectionFactory() {
        private JedisConnectionFactory factory = new JedisConnectionFactory(new RedisClusterConfiguration(source));
        // 如果需要定制连接池,可以使用下面的方式进行配置
        //    private JedisPoolConfig pool = new JedisPoolConfig();
        //    pool.setMaxIdle(8)
        //    pool.setMaxTotal(8)
        //    pool.set...
        //    factory.setPoolConfig(pool)
        return factory;
    }
    */
/*
    @Autowired
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(getClusterConfiguration());
    }
*/

}
