package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }



    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.app"))
                .build().apiInfo(getApiInfo()).host("http://127.0.0.1:8080");
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo("Address book api",
                "Sample Api for swagger",
                "1.0",
                "free to use",
                new Contact("conactA","contact-url","contactemail"),
                "Api Lisense",
                "htpp://qqqqqq",
                Collections.emptyList()
                );
    }
}
