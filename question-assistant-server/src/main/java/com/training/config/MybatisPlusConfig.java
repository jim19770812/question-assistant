package com.training.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.training.mapper")
public class MybatisPlusConfig {
}
