package bitter.config;

import bitter.domain.Bittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

//配置redis cache，当redisTemplate就绪后很简单
@Configuration
@EnableCaching
@ComponentScan
public class CachingConfig {

//    @Bean
//    public JedisConnectionFactory redisConnextionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//

    @Bean
    public RedisTemplate<String,String> cacheRedis(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Bittle.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @Autowired
    @Qualifier("cacheRedis")
    public CacheManager cacheManager(RedisTemplate cacheRedis) {
        RedisCacheManager cacheManager = new RedisCacheManager(cacheRedis);
        Map<String,Long> expiresMap=new HashMap<>();
        expiresMap.put("recentBittles",60L);
        cacheManager.setExpires(expiresMap);
        cacheManager.setDefaultExpiration(600L);
        return cacheManager;
    }
}
