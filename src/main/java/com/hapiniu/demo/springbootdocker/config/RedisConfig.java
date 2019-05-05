package com.hapiniu.demo.springbootdocker.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.hapiniu.demo.springbootdocker.model.LxmUserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author dark
 **/
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, LxmUserModel> lxmUserTokenRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, LxmUserModel> template = new RedisTemplate<>();
        FastJsonRedisSerializer<LxmUserModel> j = new FastJsonRedisSerializer<LxmUserModel>(LxmUserModel.class);
        template.setValueSerializer(j);
        template.setHashValueSerializer(j);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
