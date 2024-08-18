package org.elibrary.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    String redisDataSource;
    @Value("${redis.port}")
    int redisDsPort;
    @Value("${redis.password}")
    String redisDsPassword;

    @Bean
    public LettuceConnectionFactory lettuceRedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisDataSource, redisDsPort);
        redisStandaloneConfiguration.setPassword(redisDsPassword);
        LettuceConnectionFactory lettuceRedisConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceRedisConnectionFactory;
    }


    @Bean
        public RedisTemplate<String, Object> getRedisTemplate(){
            RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setKeySerializer(new StringRedisSerializer());//optional
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());//optional
            redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());//optional
            redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());//optional
            redisTemplate.setConnectionFactory(lettuceRedisConnectionFactory());
            return redisTemplate;
        }

    }

