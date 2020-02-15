package com.training.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

@Configuration
public class RedisConfig {
    public static class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
        public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
        private Class<T> clazz;
        public FastJson2JsonRedisSerializer(Class<T> clazz) {
            super();
            this.clazz = clazz;
        }

        public byte[] serialize(T t) throws SerializationException {
            if (t == null) {
                return new byte[0];
            }
            return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
        }

        public T deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length <= 0) {
                return null;
            }
            String str = new String(bytes, DEFAULT_CHARSET);
            return (T) JSON.parseObject(str, clazz);
        }
    }
    @Bean
    public RedisTemplate<String, String> orgainRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        GenericToStringSerializer redisSerializer = new GenericToStringSerializer(Object.class);
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean("stringRedis")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        FastJson2JsonRedisSerializer jsonRedisSerializer = new FastJson2JsonRedisSerializer(Object.class);
        template.setKeySerializer(jsonRedisSerializer);
        template.setValueSerializer(jsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
