package com.ybjx.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * spring boot程序启动类
 * @author ybjx
 * @date 2018/11/19.
 */
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@MapperScan("com.ybjx.blog.dao")
@EnableSwagger2
@EnableConfigurationProperties
public class Application {

    /**
     * spring boot 入口函数
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
