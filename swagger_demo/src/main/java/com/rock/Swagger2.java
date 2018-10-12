package com.rock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration //让Spring来加载该类配置
@EnableSwagger2 //启用Swagger2
public class Swagger2 {

    //如果需要多个文档，多加该方法就行
    @Bean
    public Docket test() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("swagger测试接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rock"))//配置需要扫描的包
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Rock工作文档")
                //创建人信息
                .contact(new Contact("rock","","knightw23@163.com"))
                //描述
                .description("工作中遇到的需求记录，如果你有好的例子，请联系：knightw23@163.com")
                //版本
                .version("1.0").build();
    }
}