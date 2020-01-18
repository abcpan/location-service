package com.abc.location;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.abc.location.dao")
@EnableCaching
public class LocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationApplication.class, args);
    }
}
