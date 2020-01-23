package com.fh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    //定义RedisTemplate
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        //配置RedisTemplate中key的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //配置RedisTemplate中value的序列化器
        redisTemplate.setValueSerializer(valueSerializer);
        //配置RedisTemplate中hash的key的序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //配置RedisTemplate中hash的value的序列化器
        redisTemplate.setHashValueSerializer(valueSerializer);
        return redisTemplate;
    }


}
