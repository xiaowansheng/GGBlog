package com.wbxnl.blog.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/26 17:56
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 设置键（key）的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        // 设置值（value）的序列化采用GenericJackson2JsonRedisSerializer
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 设置Hash key的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置Hash value的序列化方式
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}
