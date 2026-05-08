package com.assigment.Guardrails.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String,Object> tem=new RedisTemplate<>();
		tem.setConnectionFactory(factory);
		
		tem.setKeySerializer(
                new StringRedisSerializer());

        tem.setHashKeySerializer(
                new StringRedisSerializer());

        tem.setValueSerializer(
                new StringRedisSerializer());

        tem.setHashValueSerializer(
                new StringRedisSerializer());

        tem.afterPropertiesSet();

		return tem;
	}
}
