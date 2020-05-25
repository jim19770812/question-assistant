package com.training.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.nio.charset.Charset;

@Configuration
public class RedisConfig {
    private static Gson gson=new Gson();
    public static class GSon2JsonRedisSerializer<T> implements RedisSerializer<T> {
        public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
        private Class<T> clazz;
        public GSon2JsonRedisSerializer(Class<T> clazz) {
            super();
            this.clazz = clazz;
        }

        public byte[] serialize(T t) throws SerializationException {
            if (t == null) {
                return new byte[0];
            }
            return gson.toJson(t).getBytes(DEFAULT_CHARSET);
        }

        public T deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length <= 0) {
                return null;
            }
            String str = new String(bytes, DEFAULT_CHARSET);
            return (T) gson.fromJson(str, clazz);
        }
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        RedisSerializer serializer = new GSon2JsonRedisSerializer(Object.class);
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean("stringRedis")
    public RedisTemplate<String, String> stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}
