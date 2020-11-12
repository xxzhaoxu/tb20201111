package com.zx.formdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@MapperScan("com.zx.formdata.mapper")
@RestController
@SpringBootApplication
public class FormdataApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormdataApplication.class, args);
    }
}
