package com.dds.oee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author : duybv
 * Mar 2, 2019
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final String basePackage = "com.dds.oee.api";

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata())
                .tags(
                        new Tag("UserApi", "API User"),
                        new Tag("MachineApi", "API Machine"),
                        new Tag("HistoryErrorApi", "API HistoryError"),
                        new Tag("VolumeApi", "API Volume"),
                        new Tag("ScheduleApi", "Schedule Api"),
                        new Tag("CanOng01Api", "Can Ong 01 Api"),
                        new Tag("CanOng02Api", "Can Ong 02 Api"),
                        new Tag("CanOng03Api", "Can Ong 03 Api"));
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("OEE Service API documentation")
                .description("This is API documentation for working with OEE Platform")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("dds", "", "dds@gmail.com.vn"))
                .build();
    }
}
