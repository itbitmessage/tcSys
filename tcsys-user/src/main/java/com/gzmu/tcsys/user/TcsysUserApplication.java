package com.gzmu.tcsys.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mapper下的包
@MapperScan(basePackages = "com.gzmu.tcsys.user.mapper")
public class TcsysUserApplication {

    public static void main(String[] args) {
        SpringApplication.run( TcsysUserApplication.class, args );
    }

}
