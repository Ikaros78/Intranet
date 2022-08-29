package com.ohgiraffers.intranet.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ohgiraffers.intranet", annotationClass = Mapper.class)
public class MybatisConfig {
}
