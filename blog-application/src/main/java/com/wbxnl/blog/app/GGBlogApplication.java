package com.wbxnl.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:博客系统
 * @Version: 3.0
 * @Author: xiaowansheng
 * @Date: 2024/7/13 00:14
 */
//@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling //允许定时任务
@EnableAsync //开启异步编程
public class GGBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GGBlogApplication.class, args);
    }

}
