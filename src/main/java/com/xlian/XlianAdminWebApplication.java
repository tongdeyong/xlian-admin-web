package com.xlian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xlian.*.dao")
public class XlianAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(XlianAdminWebApplication.class, args);
    }

}
