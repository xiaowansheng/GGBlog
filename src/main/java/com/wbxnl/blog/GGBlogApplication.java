package com.wbxnl.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:博客系统
 * @Version: 1.0
 * @Author: xiaowansheng
 * @Date: 2023/8/10 21:01
 */
//@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling //开启定时任务类
@EnableAsync //开启异步编程
public class GGBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GGBlogApplication.class, args);
    }

}
