package com.ybjx.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger的配置
 * Created by YuBing on 2018/11/19.
 */
@Configuration
public class SwaggerConfig {

    /**
     * 创建swagger的配置bean
     * @return swagger 配置bean
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.ybjx.blog.controller"))
                .paths(PathSelectors.any()).build();

    }

    /**
     * 获取API相关的一些信息
     * @return API信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Web Blog的API接口")
                .build();
    }
}
