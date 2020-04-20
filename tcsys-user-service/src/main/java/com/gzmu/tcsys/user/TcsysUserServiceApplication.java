package com.gzmu.tcsys.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mapper
@MapperScan(basePackages = "com.gzmu.tcsys.user.mapper")
public class TcsysUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run( TcsysUserServiceApplication.class, args );
    }

}
